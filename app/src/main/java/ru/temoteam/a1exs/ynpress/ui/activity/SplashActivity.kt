package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.View
import com.androidnetworking.AndroidNetworking

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.SplashView
import ru.temoteam.a1exs.ynpress.presentation.presenter.SplashPresenter

import com.arellomobile.mvp.MvpActivity;
import com.dd.processbutton.iml.ActionProcessButton
import com.transitionseverywhere.ArcMotion
import com.transitionseverywhere.AutoTransition
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.uiThread
import ru.temoteam.a1exs.ynpress.api.objects.User
import kotlin.math.log


class SplashActivity : MvpActivity(), SplashView {


    companion object {
        const val TAG = "SplashActivity"
        fun getIntent(context: Context): Intent = Intent(context, SplashActivity::class.java)
    }

    @InjectPresenter
    lateinit var mSplashPresenter: SplashPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidNetworking.initialize(applicationContext)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        btnSignIn.onClick {
            //btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS)
            btnSignIn.progress=50
            TransitionManager.beginDelayedTransition(layout)
            editEmail.visibility=View.GONE
            editPassword.visibility=View.GONE
            mSplashPresenter.login(editEmail.text.toString(),editPassword.text.toString())
        }

    }

    override fun start(user: User?) {
        TransitionManager.beginDelayedTransition(layout, AutoTransition().setDuration(500))
        if (user==null){
            login.visibility = View.VISIBLE
        }

        else{
            hi_text.text="Hi "+user.profile!!.name
            shimmer.visibility = View.VISIBLE
        }
    }

    override fun continie() {
        startActivity(MainActivity.getIntent(this))
    }

    override fun setLoginBtnState(state: Boolean, text: String) {
        btnSignIn.progress= if (state) 100 else -1
        btnSignIn.text = text

        if (state){
            Thread.sleep(1000)
            TransitionManager.beginDelayedTransition(layout,AutoTransition())
            btnSignIn.visibility=View.GONE
        }else{
            TransitionManager.beginDelayedTransition(layout,AutoTransition())
            editEmail.visibility=View.VISIBLE
            editPassword.visibility=View.VISIBLE
            doAsync {
                Thread.sleep(1500)
                uiThread { btnSignIn.progress=0 }
            }
        }
    }



}

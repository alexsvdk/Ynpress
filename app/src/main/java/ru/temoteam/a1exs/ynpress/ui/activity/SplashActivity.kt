package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.SplashView
import ru.temoteam.a1exs.ynpress.presentation.presenter.SplashPresenter

import                  com.arellomobile.mvp.MvpActivity;
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.transitionseverywhere.ArcMotion
import com.transitionseverywhere.AutoTransition
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.activity_splash.*
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
    }

    override fun start(user: User?) {
        TransitionManager.beginDelayedTransition(layout, AutoTransition().setDuration(500))
        login.visibility = View.VISIBLE
        shimmer.visibility = View.VISIBLE
    }

    override fun continie() {
        startActivity(MainActivity.getIntent(this))
    }

    override fun setLoginBtnState(state: Boolean, text: String) {

    }

}

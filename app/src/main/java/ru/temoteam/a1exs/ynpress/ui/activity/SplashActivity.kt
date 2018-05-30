package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.SplashView
import ru.temoteam.a1exs.ynpress.presentation.presenter.SplashPresenter

import                  com.arellomobile.mvp.MvpActivity;


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
}

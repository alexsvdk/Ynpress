package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.LoginView
import ru.temoteam.a1exs.ynpress.presentation.presenter.LoginPresenter

import                  com.arellomobile.mvp.MvpAppCompatActivity;


class LoginActivity : MvpAppCompatActivity(), LoginView {
    companion object {
        const val TAG = "LoginActivity"
        fun getIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    @InjectPresenter
    lateinit var mLoginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}

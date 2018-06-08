package ru.temoteam.a1exs.ynpress.presentation.view

import com.arellomobile.mvp.MvpView
import ru.temoteam.a1exs.ynpress.api.objects.User

interface SplashView : MvpView {
    fun start(user: User?)
    fun setLoginBtnState(state: Boolean,text: String)
    fun continie()
}

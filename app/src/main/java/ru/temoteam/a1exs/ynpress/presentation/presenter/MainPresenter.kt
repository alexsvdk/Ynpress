package ru.temoteam.a1exs.ynpress.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.temoteam.a1exs.ynpress.presentation.view.MainView
import ru.temoteam.a1exs.ynpress.Global.user

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    init {
        viewState.setDrawerPic(user.profile!!.imgURL)
        viewState.setDrawerText1(user.profile!!.name+" "+user.profile!!.surname)
        viewState.setDrawerText2(user.email!!)
    }

}

package ru.temoteam.a1exs.ynpress.presentation.presenter

import android.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.temoteam.a1exs.ynpress.presentation.view.MainView
import ru.temoteam.a1exs.ynpress.Global.user
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.ui.fragment.NewsFragment
import ru.temoteam.a1exs.ynpress.ui.fragment.ProfileFragment

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val newsFragment by lazy { NewsFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    init {
        viewState.setDrawerPic(user!!.profile!!.imgURL)
        viewState.setDrawerText1(user!!.profile!!.name+" "+user!!.profile!!.surname)
        viewState.setDrawerText2(user!!.email!!)
    }

    fun getFragmentById(id: Int): Fragment? {
        return when (id){
            R.id.nav_news -> newsFragment
            R.id.nav_profile -> profileFragment

            else -> null
        }
    }

}

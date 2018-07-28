package ru.temoteam.a1exs.ynpress.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.temoteam.a1exs.ynpress.Global
import ru.temoteam.a1exs.ynpress.presentation.adapter.AchievementsRecyclerAdapter
import ru.temoteam.a1exs.ynpress.presentation.adapter.ArticlesRecyclerAdapter
import ru.temoteam.a1exs.ynpress.presentation.view.ProfileView

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    init {
        viewState.setProfilePic(Global.user!!.profile!!.imgURL)
        viewState.setName(Global.user!!.profile!!.name+" "+ Global.user!!.profile!!.surname)
        viewState.setEmail(Global.user!!.email!!)
        viewState.setArticlesAdapter(ArticlesRecyclerAdapter(Global.user!!.profile!!.articles))
        viewState.setAchievementsAdapter(AchievementsRecyclerAdapter(Global.user!!.profile!!.achievements))
    }
}

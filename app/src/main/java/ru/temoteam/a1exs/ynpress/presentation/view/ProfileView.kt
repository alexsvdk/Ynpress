package ru.temoteam.a1exs.ynpress.presentation.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arellomobile.mvp.MvpView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.temoteam.a1exs.ynpress.presentation.adapter.AchievementsRecyclerAdapter
import ru.temoteam.a1exs.ynpress.presentation.adapter.ArticlesRecyclerAdapter
import ru.temoteam.a1exs.ynpress.util.ImageLoader
import java.net.URL

interface ProfileView : MvpView {
    fun setProfilePic(bitmap: Bitmap)
    fun setName(text: String)
    fun setEmail(text: String)
    fun setArticlesAdapter(adapter: ArticlesRecyclerAdapter)
    fun setAchievementsAdapter(adapter: AchievementsRecyclerAdapter)

    fun setProfilePic(url: String){
        ImageLoader.getBitmapAsync(url) {setProfilePic(it)}
    }

}

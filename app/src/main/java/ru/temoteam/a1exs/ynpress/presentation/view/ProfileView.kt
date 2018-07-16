package ru.temoteam.a1exs.ynpress.presentation.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arellomobile.mvp.MvpView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

interface ProfileView : MvpView {
    fun setProfilePic(bitmap: Bitmap)
    fun setName(text: String)
    fun setEmail(text: String)

    fun setProfilePic(url: String){
        doAsync {
            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
            uiThread { setProfilePic(bitmap) }
        }
    }
}

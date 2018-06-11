package ru.temoteam.a1exs.ynpress.presentation.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arellomobile.mvp.MvpView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

interface MainView : MvpView {
    fun setDrawerPic(bitmap: Bitmap)
    fun setDrawerText1(text: String)
    fun setDrawerText2(text: String)

    fun setDrawerPic(url: String){
        doAsync {
            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
            uiThread { setDrawerPic(bitmap) }
        }
    }
}

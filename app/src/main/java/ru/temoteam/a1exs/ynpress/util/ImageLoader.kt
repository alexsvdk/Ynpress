package ru.temoteam.a1exs.ynpress.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.net.URL
import java.util.*

@SuppressLint("StaticFieldLeak")
object ImageLoader {

    private val preloadDeque = ArrayDeque<String>()
    private var preloadStatus = 0
    private lateinit var context: Context

    fun init(context: Context){
        this.context = context
        if (!File(context.filesDir,"cache"+File.separator+"img").exists()) File(context.filesDir,"cache"+File.separator+"img").mkdirs()
    }

    fun addToPreLoad(url: String){
        preloadDeque.push(url)
        if (preloadStatus == 0) doAsync {
            preloadStatus = 1
            while (!preloadDeque.isEmpty()){
                try {
                    val url = preloadDeque.poll()
                    if (!isFileCached(url)) cacheFile(url)
                } catch (e: Exception){ }
            }
            preloadStatus = 0
        }
    }

    fun addToPreLoad(urls: List<String>){
        urls.forEach { addToPreLoad(it) }
    }

    fun getBitmapAsync(url: String,callback: (Bitmap) -> (Unit)){
        doAsync { getBitmap(url).let{bitmap -> uiThread { callback(bitmap) }} }
    }

    fun getBitmap(url: String): Bitmap{
        if (!isFileCached(url)) cacheFile(url)
        if (preloadDeque.contains(url)) preloadDeque.remove(url)
        return BitmapFactory.decodeStream(getCachedFile(url).inputStream())
    }


    private fun cacheFile(url: String):File{
        val file = File(context.filesDir,"cache"+File.separator+"img"+File.separator+url.substringAfterLast("/"))
        file.createNewFile()

        file.writeBytes(URL(url).openConnection().getInputStream().readBytes())
        return file
    }

    private fun getCachedFile(url: String):File{
        return File (context.filesDir,"cache"+File.separator+"img"+File.separator+url.substringAfterLast("/"))
    }

    private fun isFileCached(url: String):Boolean{
        return  File (context.filesDir,"cache"+File.separator+"img"+File.separator+url.substringAfterLast("/")).exists()
    }



}
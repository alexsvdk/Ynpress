package ru.temoteam.a1exs.ynpress.api.objects

import android.app.Fragment
import com.google.gson.Gson

data class Article(val title:String,val url:String,val imgURL:String?=null,val date:String?=null,val category:String?=null,val status:Boolean = true) {

    data class Content(
        val author: String,
        val publishDate: String,
        val mainImgURL: String,
        val views: String,
        val likes:String = "0",
        val dislikes:String = ""
    ){
        val articleParts = mutableListOf<ArticlePart>()

        override fun toString(): String {
            return Gson().toJson(this)
        }
    }


    abstract class ArticlePart{
        abstract val contentFragment: Fragment
    }

    data class TextArticlePart(val text:String, val isBold:Boolean = false): ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

    data class GalleryArticlePart(val imgUrls:MutableList<String> = mutableListOf()):ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

    class ImgArticlePart(val imgUrl: String): ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

    class GeoArticlePart: ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

    data class VideoArticlePart(val videoURL:String): ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

    data class AudioArticlePart(val audioUrl:String): ArticlePart() {
        override val contentFragment: Fragment by lazy { Fragment() }
    }

}
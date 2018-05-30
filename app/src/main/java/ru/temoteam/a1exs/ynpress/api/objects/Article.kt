package ru.temoteam.a1exs.ynpress.api.objects

import com.google.gson.Gson

data class Article(val title:String,val url:String,val imgURL:String?=null,val date:String?=null,val category:String?=null) {

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


    open class ArticlePart{}
    data class TextArticlePart(val text:String, val isBold:Boolean = false): ArticlePart()
    data class GalleryArticlePart(val imgUrls:MutableList<String> = mutableListOf()):ArticlePart()
    class ImgArticlePart: ArticlePart() {}
    class GeoArticlePart: ArticlePart() {}

    data class VideoArticlePart(val videoURL:String): ArticlePart() {}

}
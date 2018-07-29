package ru.temoteam.a1exs.ynpress.api.objects

import android.support.annotation.Keep
import android.util.Log
import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester
import java.util.*
import kotlin.collections.HashMap

class User() {

    constructor(email:String,password:String): this(){
        this.email = email
        this.password = password
        login()
        activateAccount()
        loadProfile()
    }

    constructor(hashMap: HashMap<String,Any>): this(){
        this.email = hashMap["email"] as String
        this.password = hashMap["password"] as String
        this.cookie = hashMap["cookie"] as String
        this.profile = Profile(hashMap["profile"] as HashMap<String, Any>)
    }

    var email: String? = null
    var password:String? = null
    var cookie:String? = null
    var profile: Profile? = null

    fun login(){
        val response = Requester.auth(email!!, password!!)
        cookie = response.setCoocie!!.substring(response.setCoocie.indexOf("wordpress_")).substringBefore(";")
    }

    fun activateAccount(){
        Requester.cookie=cookie!!
    }

    fun loadProfile(){
        profile=Parser.parseProfile(Requester.profile()!!.text)
    }

    data class Profile(
            val name: String,
            val surname: String,
            val birthday: String,
            val region: String,
            val regionId: String,
            val phone: String,
            val imgURL: String,
            val rating: String,
            val articles: List<Article>,
            val achievements: List<String>
    ){
        constructor(hashMap: HashMap<String, Any>): this(
                hashMap["name"]!! as String,
                hashMap["surname"]!! as String,
                hashMap["birthday"]!! as String,
                hashMap["region"]!! as String,
                hashMap["regionId"]!! as String,
                hashMap["phone"]!! as String,
                hashMap["imgURL"]!! as String,
                hashMap["rating"]!! as String,
                (hashMap["articles"] as List<HashMap<String,String>>).map { Article(it["title"]!!, it["url"]!!) },
                hashMap["achievements"] as List<String>

        )
    }
}
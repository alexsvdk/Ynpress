package ru.temoteam.a1exs.ynpress.api.objects

import android.support.annotation.Keep
import android.util.Log
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester

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
        this.profile = Profile(hashMap["profile"] as HashMap<String, String>)
    }

    var email: String? = null
    var password:String? = null
    var cookie:String? = null
    var profile: Profile? = null

    fun login(){
        val response = Requester.auth(email!!, password!!)
        cookie = response!!.header("Set-Cookie")!!.substring(response.header("Set-Cookie")!!.indexOf("wordpress_")).substringBefore(";")
    }

    fun activateAccount(){
        Requester.cookie=cookie!!
    }

    fun loadProfile(){
        profile=Parser.parseProfile(Requester.profile()!!.body()!!.string())
    }

    data class Profile(
            val name: String,
            val surname: String,
            val birthday: String,
            val region: String,
            val regionId: String,
            val phone: String,
            val imgURL: String,
            val rating: String
    ){
        constructor(hashMap: HashMap<String, String>): this(
                hashMap["name"]!!,
                hashMap["surname"]!!,
                hashMap["birthday"]!!,
                hashMap["region"]!!,
                hashMap["regionId"]!!,
                hashMap["phone"]!!,
                hashMap["imgURL"]!!,
                hashMap["rating"]!!
        )
    }
}
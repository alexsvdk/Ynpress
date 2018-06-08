package ru.temoteam.a1exs.ynpress.api.objects

import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester

data class User(val email: String,private val password:String) {

    val cookie: String
    var profile: Profile? = null

    init {
        val response = Requester.auth(email,password)
        cookie=response.setCoocie!!.substring(response.setCoocie.indexOf("wordpress_")).substringBefore(";")
    }

    fun activateAccount(){
        Requester.cookie=cookie
    }

    fun loadProfile(){
        profile=Parser.parseProfile(Requester.profile().text)
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
    )
}
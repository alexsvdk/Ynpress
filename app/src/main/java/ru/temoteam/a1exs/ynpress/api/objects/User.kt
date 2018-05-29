package ru.temoteam.a1exs.ynpress.api.objects

import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester

class User(val email: String,private val password:String) {

    val cookie: String
    val profile: Profile by lazy { Parser.parseProfile(Requester.PersonalRequester(this).profile().text) }

    init {
        val response = Requester.auth(email,password)
        cookie=response.setCoocie!!.substring(response.setCoocie.indexOf("wordpress_")).substringBefore(";")
        println(profile)
    }

    class Profile(
            val name: String,
            val surname: String,
            val birthday: String,
            val region: String,
            val regionId: String,
            val phone: String,
            val imgURL: String,
            val rating: String
    ){
        override fun toString(): String {
            return "$name\n$surname\n$birthday\n$region\n$regionId\n$phone\n$imgURL\n$rating"
        }
    }
}
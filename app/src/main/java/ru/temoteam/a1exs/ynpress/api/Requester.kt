package ru.temoteam.a1exs.ynpress.api

import ru.temoteam.a1exs.ynpress.api.Multipart.Response
import ru.temoteam.a1exs.ynpress.api.objects.User


object Requester {
    const val baseUrl = "http://ynpress.com"

    private fun basereq(patch:String, headers: Map<String,String> = emptyMap(), params: Map<String,String> = emptyMap(),cookie:String=""): Response {
        val request = Multipart(baseUrl+patch,cookie)
        request.addHeaderFields(headers)
        request.addFormFields(params)
        return request.upload()
    }

    fun auth(email: String,password:String):Response{
       return basereq("/ajax/auth.php",params = mapOf("yp-email" to email, "yp-password" to password))
    }

    class PersonalRequester(val user: User) {
        fun profile():Response{
            return basereq("/profile/",cookie = user.cookie)
        }
    }

}
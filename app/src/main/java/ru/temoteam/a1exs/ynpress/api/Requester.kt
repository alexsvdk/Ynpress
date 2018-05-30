package ru.temoteam.a1exs.ynpress.api

import ru.temoteam.a1exs.ynpress.api.Multipart.Response


object Requester {
    const val baseUrl = "http://ynpress.com"
    var cookie: String = ""

    fun basereq(patch:String, headers: Map<String,String> = emptyMap(), params: Map<String,String> = emptyMap()): Response {
        val request = Multipart(if (patch.startsWith("http")) patch else baseUrl+patch,cookie)
        request.addHeaderFields(headers)
        request.addFormFields(params)
        return request.upload()
    }

    fun read(page: Int):Response{
        return basereq("/read/page/$page/")
    }

    fun auth(email: String,password:String):Response{
       return basereq("/ajax/auth.php",params = mapOf("yp-email" to email, "yp-password" to password))
    }

    fun profile():Response{
        return basereq("/profile/")
    }




}
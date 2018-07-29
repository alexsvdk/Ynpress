package ru.temoteam.a1exs.ynpress.api

import android.util.Log
import ru.temoteam.a1exs.ynpress.util.Multipart


object Requester {

    const val baseUrl = "http://ynpress.com"
    var cookie: String = ""

    fun basereq(patch:String, headers: Map<String,String> = emptyMap(), params: Map<String,String> = emptyMap()): Multipart.Response {
        val request = Multipart(if (patch.startsWith("http")) patch else baseUrl+patch, cookie)

        request.addHeaderFields(headers)
        request.addFormField("text","text")
        request.addFormFields(params)

        return request.upload()
    }

    fun read(page: Int):Multipart.Response{
        return basereq("/read/page/$page/")
    }

    fun auth(email: String,password:String):Multipart.Response{
        return basereq("/ajax/auth.php",params = mapOf("yp-email" to email, "yp-password" to password))
    }

    fun profile():Multipart.Response{
        return basereq("/profile/")
    }

}
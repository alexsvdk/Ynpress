package ru.temoteam.a1exs.ynpress.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseListener
import okhttp3.*


object Requester {

    const val baseUrl = "http://ynpress.com"
    var cookie: String = ""

    val client = OkHttpClient.Builder().followRedirects(false).followSslRedirects(false).build()

    fun basereq(patch:String, headers: Map<String,String> = emptyMap(), params: Map<String,String> = emptyMap()): Response? {

        val upload = AndroidNetworking.upload(if (patch.startsWith("http")) patch else baseUrl+patch).setOkHttpClient(client).addHeaders("Cookie", cookie)
        upload.addMultipartParameter("text","text")
        params.forEach { t, u -> upload.addMultipartParameter(t,u) }
        val response = upload.build().executeForOkHttpResponse()
        Log.i("RESPONSE", response.isSuccess.toString())
        if (!response.isSuccess) Log.i("RESPONSE", response.error.message)
        return response.okHttpResponse
    }

    fun read(page: Int):Response?{
        return basereq("/read/page/$page/")
    }

    fun auth(email: String,password:String):Response?{
       return basereq("/ajax/auth.php",params = mapOf("yp-email" to email, "yp-password" to password))
    }

    fun profile():Response?{
        return basereq("/profile/")
    }

}
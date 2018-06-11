package ru.temoteam.a1exs.ynpress

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.temoteam.a1exs.ynpress.api.objects.User

object Global {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    lateinit var user: User
}


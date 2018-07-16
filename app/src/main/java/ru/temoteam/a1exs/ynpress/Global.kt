package ru.temoteam.a1exs.ynpress

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.temoteam.a1exs.ynpress.api.objects.User

object Global {
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var user: User? = null
        get() {if (field==null) getUser();while (field==null) Thread.sleep(50); return field}


    fun getUser(){
        database!!.reference.child(auth!!.currentUser!!.uid).child("account").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                user = User(p0.value as HashMap<String, Any>)
                user!!.activateAccount()
            }
        })
    }

}



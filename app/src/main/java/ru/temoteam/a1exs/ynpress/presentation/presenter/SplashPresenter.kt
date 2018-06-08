package ru.temoteam.a1exs.ynpress.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.temoteam.a1exs.ynpress.api.Requester
import ru.temoteam.a1exs.ynpress.api.objects.User
import ru.temoteam.a1exs.ynpress.presentation.view.SplashView

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    init {
        if (auth.currentUser==null)
            doAsync {
                Thread.sleep(700)
                uiThread { viewState.start(null) }
            }
        else{
            database.reference.child(auth!!.currentUser!!.email!!).child("account").addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) { }

                override fun onDataChange(p0: DataSnapshot) {
                    val user = p0.value as User
                    user.activateAccount()
                    viewState.start(user)
                    doAsync {
                        user.loadProfile()
                        Thread.sleep(1500)
                        uiThread { viewState.continie() }
                    }
                }

            })
        }
    }

    fun login(email:String,password:String) {
        doAsync {
            try {
                val user = User(email,password)
                user.activateAccount()
                user.loadProfile()
                uiThread {
                    viewState.start(user)
                    auth.signInWithEmailAndPassword(email,password).addOnFailureListener {
                        auth.createUserWithEmailAndPassword(email,password)
                    }
                    database.reference.child(auth!!.currentUser!!.email!!).child("account").setValue(user)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

package ru.temoteam.a1exs.ynpress.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.temoteam.a1exs.ynpress.Global
import ru.temoteam.a1exs.ynpress.api.Requester
import ru.temoteam.a1exs.ynpress.api.objects.User
import ru.temoteam.a1exs.ynpress.presentation.view.SplashView

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    init {
        Global.auth = auth
        Global.database = database
        if (auth.currentUser==null)
            doAsync {
                Thread.sleep(700)
                uiThread { viewState.start(null) }
            }
        else{
            database.reference.child(auth!!.currentUser!!.uid).child("account").addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) { }

                override fun onDataChange(p0: DataSnapshot) {
                    val user = User(p0.value as HashMap<String, Any>)
                    user.activateAccount()
                    viewState.start(user)
                    Global.user=user
                    doAsync {
                        try {
                            user.loadProfile()
                        } catch (e: IndexOutOfBoundsException){
                            try {
                                user.login()
                                user.activateAccount()
                                user.loadProfile()
                            } catch (e:Exception){
                                viewState.start(null)
                                viewState.setEmain(user.email!!)
                            }
                        }

                        uiThread { viewState.continie() }
                    }
                }

            })
        }
    }

    fun login(email:String,password:String) {
        doAsync {
            try {
                Log.i("email",email)
                Log.i("password", password)

                val user = User(email,password)
                uiThread {
                    Global.user=user
                    viewState.start(user)
                    auth.signInWithEmailAndPassword(email,password).addOnFailureListener {
                        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                            database.reference.child(auth!!.currentUser!!.uid).child("account").setValue(user)
                            viewState.setLoginBtnState(true,"Success")
                            doAsync {
                                Thread.sleep(1500)
                                uiThread { viewState.continie() }
                            }

                        }
                    }.addOnSuccessListener {
                        database.reference.child(auth!!.currentUser!!.uid).child("account").setValue(user)
                        viewState.setLoginBtnState(true,"Success")
                        doAsync {
                            Thread.sleep(1500)
                            uiThread { viewState.continie() }
                        }
                    }

                }

            }catch (e: IndexOutOfBoundsException){
                e.printStackTrace()
                uiThread {
                    viewState.setLoginBtnState(false,"Bad email or password")
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
package ru.temoteam.a1exs.ynpress.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.LoginView
import ru.temoteam.a1exs.ynpress.presentation.presenter.LoginPresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class LoginFragment : MvpFragment(), LoginView {
    companion object {
        const val TAG = "LoginFragment"

        fun newInstance(): LoginFragment {
            val fragment: LoginFragment = LoginFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mLoginPresenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

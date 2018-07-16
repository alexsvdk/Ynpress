package ru.temoteam.a1exs.ynpress.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.LibraryView
import ru.temoteam.a1exs.ynpress.presentation.presenter.LibraryPresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class LibraryFragment : MvpFragment(), LibraryView {
    companion object {
        const val TAG = "LibraryFragment"

        fun newInstance(): LibraryFragment {
            val fragment: LibraryFragment = LibraryFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mLibraryPresenter: LibraryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

package ru.temoteam.a1exs.ynpress.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.ReadView
import ru.temoteam.a1exs.ynpress.presentation.presenter.ReadPresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_read.*
import ru.temoteam.a1exs.ynpress.presentation.adapter.ReadAdapter
import ru.temoteam.a1exs.ynpress.ui.activity.MainActivity
import android.support.v7.widget.RecyclerView





class ReadFragment : MvpFragment(), ReadView {

    companion object {
        const val TAG = "ReadFragment"

        fun newInstance(): ReadFragment {
            val fragment: ReadFragment = ReadFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mReadPresenter: ReadPresenter

    val layoutManager = LinearLayoutManager(activity)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_read, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var mLastFirstVisibleItem = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {}

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentFirstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {
                    //(activity as MainActivity).actionBar!!.hide()
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    //(activity as MainActivity).actionBar!!.show()
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem
            }
        })

    }

    override fun setRecyclerAdapter(adapter: ReadAdapter) {
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }
}

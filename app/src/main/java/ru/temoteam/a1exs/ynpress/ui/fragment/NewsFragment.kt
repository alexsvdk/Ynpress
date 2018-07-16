package ru.temoteam.a1exs.ynpress.ui.fragment

import android.media.audiofx.BassBoost
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.NewsView
import ru.temoteam.a1exs.ynpress.presentation.presenter.NewsPresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_news.*
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.temoteam.a1exs.ynpress.presentation.adapter.ViewPagerAdapter


class NewsFragment : MvpFragment(), NewsView {

    companion object {
        const val TAG = "NewsFragment"

        fun newInstance(): NewsFragment {
            val fragment: NewsFragment = NewsFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mNewsPresenter: NewsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        val adapter = ViewPagerAdapter(fragmentManager)
        adapter.addFragment(ReadFragment(),"Read")
        adapter.addFragment(LibraryFragment(),"Library")
        pager.adapter=adapter
        tab_layout.setupWithViewPager(pager)

        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

    }




}

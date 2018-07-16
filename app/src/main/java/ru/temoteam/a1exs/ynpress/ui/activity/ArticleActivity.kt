package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.ArticleView
import ru.temoteam.a1exs.ynpress.presentation.presenter.ArticlePresenter

import com.klinker.android.sliding.SlidingActivity


class ArticleActivity : SlidingActivity(), ArticleView {

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_article)
    }

    companion object {
        const val TAG = "ArticleActivity"
        fun getIntent(context: Context): Intent = Intent(context, ArticleActivity::class.java)
    }

    @InjectPresenter
    lateinit var mArticlePresenter: ArticlePresenter



}

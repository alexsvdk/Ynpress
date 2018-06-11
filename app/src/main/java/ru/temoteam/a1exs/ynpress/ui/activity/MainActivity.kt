package ru.temoteam.a1exs.ynpress.ui.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.MainView
import ru.temoteam.a1exs.ynpress.presentation.presenter.MainPresenter

import                  com.arellomobile.mvp.MvpAppCompatActivity;
import com.ms_square.etsyblur.Blur
import com.ms_square.etsyblur.BlurConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.*




class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        const val TAG = "MainActivity"
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var blur: Blur


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        blur =  Blur(this, BlurConfig.Builder().radius(25).downScaleFactor(10).overlayColor(0).build())
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open,  R.string.drawer_close)
        nvView.setNavigationItemSelectedListener(
                NavigationView.OnNavigationItemSelectedListener { menuItem ->

                    true
                })

        drawer_layout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun setDrawerPic(bitmap: Bitmap) {
        nvView.getHeaderView(0).profilepic.setImageBitmap(bitmap)
        blur.execute(bitmap,false,{nvView.getHeaderView(0).back.setImageBitmap(it)})

    }

    override fun setDrawerText1(text: String) {
        nvView.getHeaderView(0).first_field.text=text
    }

    override fun setDrawerText2(text: String) {
        nvView.getHeaderView(0).second_field.text=text
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}

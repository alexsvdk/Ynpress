package ru.temoteam.a1exs.ynpress.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.presentation.view.ProfileView
import ru.temoteam.a1exs.ynpress.presentation.presenter.ProfilePresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ms_square.etsyblur.Blur
import com.ms_square.etsyblur.BlurConfig
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.imageBitmap

class ProfileFragment : MvpFragment(), ProfileView {

    companion object {
        const val TAG = "ProfileFragment"

        fun newInstance(): ProfileFragment {
            val fragment: ProfileFragment = ProfileFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mProfilePresenter: ProfilePresenter
    private lateinit var blur: Blur

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blur =  Blur(activity, BlurConfig.Builder().radius(25).downScaleFactor(10).overlayColor(0).build())
    }

    override fun setProfilePic(bitmap: Bitmap) {
        profile.imageBitmap = bitmap
        blur.execute(bitmap,false) {header_cover_image.imageBitmap = it}
    }

    override fun setName(text: String) {
        name.text = text
    }

    override fun setEmail(text: String) {
        designation.text = text
    }
}

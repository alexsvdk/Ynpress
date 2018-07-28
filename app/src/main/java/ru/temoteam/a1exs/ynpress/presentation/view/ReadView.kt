package ru.temoteam.a1exs.ynpress.presentation.view

import com.arellomobile.mvp.MvpView
import ru.temoteam.a1exs.ynpress.presentation.adapter.ReadRecyclerAdapter

interface ReadView : MvpView {
    fun setRecyclerAdapter(recyclerAdapter: ReadRecyclerAdapter)
}

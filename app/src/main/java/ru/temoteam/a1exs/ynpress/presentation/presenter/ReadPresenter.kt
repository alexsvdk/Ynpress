package ru.temoteam.a1exs.ynpress.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.temoteam.a1exs.ynpress.presentation.adapter.ReadRecyclerAdapter
import ru.temoteam.a1exs.ynpress.presentation.view.ReadView

@InjectViewState
class ReadPresenter : MvpPresenter<ReadView>() {
    val adapter = ReadRecyclerAdapter()
    init {
        viewState.setRecyclerAdapter(adapter)
    }
}

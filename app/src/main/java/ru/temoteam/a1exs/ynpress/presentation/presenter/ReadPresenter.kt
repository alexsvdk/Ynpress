package ru.temoteam.a1exs.ynpress.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.temoteam.a1exs.ynpress.presentation.adapter.ReadAdapter
import ru.temoteam.a1exs.ynpress.presentation.view.ReadView

@InjectViewState
class ReadPresenter : MvpPresenter<ReadView>() {
    val adapter = ReadAdapter()
    init {
        viewState.setRecyclerAdapter(adapter)
    }
}

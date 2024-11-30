package com.kigi.baseview

import com.kigi.baseview.viewmodel.BaseViewModel

interface IViewModel<out ViewModel : BaseViewModel> {
    val viewModel: ViewModel
}
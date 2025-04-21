package com.ndewon.spendless.presentation.di

import com.ndewon.spendless.presentation.screens.createusername.CreateUserNameViewModel
import com.ndewon.spendless.presentation.screens.repeatpin.RepeatPinViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appPresentationModule = module {
    viewModel {
        CreateUserNameViewModel(get())
    }

    viewModel {
        RepeatPinViewModel(get())
    }
}
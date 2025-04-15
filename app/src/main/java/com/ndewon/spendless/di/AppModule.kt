package com.ndewon.spendless.di

import com.ndewon.spendless.data.di.appDataModule
import com.ndewon.spendless.domain.di.appDomainModule
import com.ndewon.spendless.presentation.di.appPresentationModule

val appModule = appDataModule + appDomainModule + appPresentationModule
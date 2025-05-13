package com.ndewon.spendless.domain.di

import com.ndewon.spendless.data.mapper.PreferenceDataMapper
import com.ndewon.spendless.data.mapper.UserDataMapper
import com.ndewon.spendless.domain.repository.CurrencyRepository
import com.ndewon.spendless.domain.repository.PreferenceRepository
import com.ndewon.spendless.domain.repository.UserRepository
import com.ndewon.spendless.domain.usecase.CurrenciesUseCase
import com.ndewon.spendless.domain.usecase.PreferenceUseCase
import com.ndewon.spendless.domain.usecase.UserRegistrationUseCase
import org.koin.dsl.module

val appDomainModule = module {
    single {
        UserDataMapper()
    }

    single {
        PreferenceDataMapper()
    }

    single<UserRepository> {
        UserRegistrationUseCase(get(), get())
    }

    single<PreferenceRepository> {
        PreferenceUseCase(get(), get())
    }

    single<CurrencyRepository> {
        CurrenciesUseCase()
    }
}
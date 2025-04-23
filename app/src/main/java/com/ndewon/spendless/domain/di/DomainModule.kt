package com.ndewon.spendless.domain.di

import com.ndewon.spendless.data.mapper.UserDataMapper
import com.ndewon.spendless.domain.repository.UserRepository
import com.ndewon.spendless.domain.usecase.UserRegistrationUseCase
import org.koin.dsl.module

val appDomainModule = module {
    single {
        UserDataMapper()
    }

    single<UserRepository> {
        UserRegistrationUseCase(get(), get())
    }
}
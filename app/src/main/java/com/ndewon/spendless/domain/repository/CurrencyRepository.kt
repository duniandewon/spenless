package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.Currency

interface CurrencyRepository {
    fun getCurrencies(): List<Currency>
}
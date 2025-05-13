package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.domain.models.Currency
import com.ndewon.spendless.domain.repository.CurrencyRepository

class GetCurrenciesUseCaseO() : CurrencyRepository {
    override fun getCurrencies(): List<Currency> {
        return listOf(
            Currency("Rp", "Indonesian Rupiah", "IDR")
        )
    }
}
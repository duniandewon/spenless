package com.ndewon.spendless.data.mapper

import com.ndewon.spendless.data.local.entity.PreferenceEntity
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.models.ThousandsSeparator

class PreferenceDataMapper {
    fun preferenceDomainToEntity(preference: Preference): PreferenceEntity = PreferenceEntity(
        preference.id,
        preference.currency,
        preference.expenseFormat.name,
        preference.decimalSeparator.name,
        preference.thousandSeparator.name
    )

    fun preferenceEntityToDomain(preferenceEntity: PreferenceEntity): Preference = Preference(
        preferenceEntity.id,
        preferenceEntity.currency,
        ExpenseFormat.valueOf(preferenceEntity.expenseFormat),
        DecimalSeparator.valueOf(preferenceEntity.decimalSeparator),
        ThousandsSeparator.valueOf(preferenceEntity.thousandSeparator)
    )
}
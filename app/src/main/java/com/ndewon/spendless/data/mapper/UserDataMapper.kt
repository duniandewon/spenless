package com.ndewon.spendless.data.mapper

import com.ndewon.spendless.data.local.entity.UserEntity
import com.ndewon.spendless.domain.models.User

class UserDataMapper {
    fun userDomainToEntity(user: User): UserEntity =
        UserEntity(user.id, user.username, user.pinHash, user.accountBalance)

    fun userEntityToDomain(userEntity: UserEntity): User =
        User(userEntity.id, userEntity.username, userEntity.pin, userEntity.accountBalance)
}
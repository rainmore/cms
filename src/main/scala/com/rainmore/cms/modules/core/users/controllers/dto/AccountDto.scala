package com.rainmore.cms.modules.core.users.controllers.dto

import com.rainmore.cms.models.core.users.Account
import com.rainmore.cms.models.core.users.Status.Status

object AccountDto {
    def apply(account: Account): AccountDto = {
        AccountDto(
            account.id,
            account.firstName,
            account.lastName,
            account.email,
            account.status,
        )
    }
}

case class AccountDto
(
    id: String,
    firstName: String,
    lastName: String,
    email: String,
    status: Status
) {
    def getName: String = "%s %s".format(firstName, lastName).trim
}
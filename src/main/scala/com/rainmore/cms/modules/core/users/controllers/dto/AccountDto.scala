package com.rainmore.cms.modules.core.users.controllers.dto

import com.rainmore.cms.domains.core.users.{Account, Status}

object AccountDto {
    def apply(account: Account): AccountDto = {
        AccountDto(
            account.getId,
            account.getFirstName,
            account.getLastName,
            account.getEmail,
            account.getStatus,
        )
    }
}

case class AccountDto
(
    id: Long,
    firstName: String,
    lastName: String,
    email: String,
    status: Status
) {
    def getName: String = "%s %s".format(firstName, lastName).trim
}
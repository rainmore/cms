package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.users.{Account, AccountStatus}

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
    status: AccountStatus
) {
    def getName: String = "%s %s".format(firstName, lastName).trim
}
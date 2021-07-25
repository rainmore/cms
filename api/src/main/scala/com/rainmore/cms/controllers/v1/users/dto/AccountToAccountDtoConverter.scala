package com.rainmore.cms.controllers.v1.users.dto

import com.rainmore.cms.domains.users.Account
import org.springframework.core.convert.converter.Converter

class AccountToAccountDtoConverter extends Converter[Account, AccountDto] {

    override def convert(source: Account): AccountDto = {
        AccountDto(source)
    }
}

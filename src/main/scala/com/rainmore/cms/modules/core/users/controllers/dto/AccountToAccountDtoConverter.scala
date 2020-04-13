package com.rainmore.cms.modules.core.users.controllers.dto

import com.rainmore.cms.domains.core.users.Account
import org.springframework.core.convert.converter.Converter

class AccountToAccountDtoConverter extends Converter[Account, AccountDto] {

    override def convert(source: Account): AccountDto = {
        AccountDto(source)
    }
}

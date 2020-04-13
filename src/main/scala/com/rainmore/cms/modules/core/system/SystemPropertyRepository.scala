package com.rainmore.cms.modules.core.system

import java.lang.{String => JString}

import com.rainmore.cms.domains.core.system.security.{QSystemProperty, SystemProperty}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait SystemPropertyRepository extends BaseRepository[SystemProperty, JString, QSystemProperty]
    with SystemPropertyRepositoryCustom

trait SystemPropertyRepositoryCustom

class AccountRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[SystemProperty, JString, QSystemProperty](classOf[SystemProperty], QSystemProperty.systemProperty)
        with SystemPropertyRepositoryCustom


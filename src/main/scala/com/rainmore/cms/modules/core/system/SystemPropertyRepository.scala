package com.rainmore.cms.modules.core.system

import com.rainmore.cms.domains.system.{QSystemProperty, SystemProperty}

import java.lang.{String => JString}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait SystemPropertyRepository
    extends BaseRepository[SystemProperty, JString, QSystemProperty]
    with SystemPropertyRepositoryCustom

trait SystemPropertyRepositoryCustom

class SystemPropertyRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[SystemProperty, JString, QSystemProperty](classOf[SystemProperty], QSystemProperty.systemProperty)
        with SystemPropertyRepositoryCustom


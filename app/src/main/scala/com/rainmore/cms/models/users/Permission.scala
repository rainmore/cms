package com.rainmore.cms.models.users

import com.rainmore.cms.models.{Model, Nameable}
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("userPermissions")
class Permission extends Model with Nameable {

    @Id
    var id: String = _
    var name: String = _

    var parent: Option[Permission] = _

    override def getName(): String = name

}

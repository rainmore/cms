package com.rainmore.cms.models.core.users

import com.rainmore.cms.models.core.{Model, Nameable}
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import scala.collection.mutable.Set

@Document("usersRoles")
class Role extends Model with Nameable {
    @Id
    var id: String = _
    var name: String = _
    var isAlmighty: Boolean = false

    var parent: Option[Role] = _
    var permissions: Set[Permission] = Set.empty[Permission]

    override def getName(): String = name
}

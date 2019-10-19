package com.rainmore.cms.models.users

import com.rainmore.cms.models.{Model, Nameable, users}
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import scala.collection.mutable.Set

@Document("usersAccounts")
class Account extends Model with Nameable {

    @Id
    var id: String = _

    var firstName: String          = _
    var lastName: String           = _
    var email: String              = _
    var password: String           = _
    var status: Status.Value       = Status.Active

    var permissions: Set[Permission] = Set.empty[Permission]

    override def getName(): String = "%s %s".format(firstName, lastName).trim
}


object Status extends Enumeration {
    type Status = Value
    val Active, Disabled, Suspended = Value

    def isActive: Boolean = Active == this
    def isDisabled: Boolean = Disabled == this
    def isSuspended: Boolean = Suspended == this
}

package com.rainmore.cms.models.core.users

import com.rainmore.cms.models.core.{Model, Nameable}
import org.springframework.data.annotation.{Id, Transient}
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.collection.mutable.Set

@Document("usersAccounts")
class Account extends Model with Nameable {

    @Id
    var id: String = _

    @Indexed
    var firstName: String          = _
    @Indexed
    var lastName: String           = _

    @Indexed
    var email: String              = _
    var password: String           = _

    @Indexed
    var status: Status.Value       = Status.Active

    var permissions: Set[Permission] = Set.empty[Permission]

    @Transient
    override def getName(): String = "%s %s".format(firstName, lastName).trim
}


object Status extends Enumeration {
    type Status = Value
    val Active, Disabled, Suspended = Value

    def isActive: Boolean = Active == this
    def isDisabled: Boolean = Disabled == this
    def isSuspended: Boolean = Suspended == this
}

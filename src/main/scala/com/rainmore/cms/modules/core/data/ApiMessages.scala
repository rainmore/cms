package com.rainmore.cms.modules.core.data

import java.io.Serializable

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonValue}
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.rainmore.cms.modules.core.data.FlashMessageKind.Kind

import scala.collection.mutable.Stack


class ApiMessages extends Serializable {

    private val messages: Stack[FlashMessage] = Stack.empty

    def reset: Unit = messages.removeAll()

    def isEmpty: Boolean = messages.isEmpty

    def nonEmpty: Boolean = messages.nonEmpty

    def add(flashMessage: FlashMessage): ApiMessages = {
        messages.addOne(flashMessage)
        this
    }

    def add(flashMessages: Seq[FlashMessage]): ApiMessages = {
        messages.addAll(flashMessages)
        this
    }

    def toJsonMap: Map[Kind, Seq[String]] = {
        getMessages.groupMap(_.kind)(_.message)
    }

    @JsonValue
    def getMessages: Seq[FlashMessage] = messages.toSeq
}

object FlashMessage {
    def info(message: String): FlashMessage = FlashMessage(FlashMessageKind.Info, message)
    def error(message: String): FlashMessage = FlashMessage(FlashMessageKind.Error, message)
    def success(message: String): FlashMessage = FlashMessage(FlashMessageKind.Success, message)
    def warning(message: String): FlashMessage = FlashMessage(FlashMessageKind.Warning, message)
}

case class FlashMessage(@JsonScalaEnumeration(classOf[FlashMessageKindType]) kind: Kind, message: String)


class FlashMessageKindType extends TypeReference[FlashMessageKind.type]
object FlashMessageKind extends Enumeration {
    type Kind = Value
    val Error, Info, Success, Warning = Value
}


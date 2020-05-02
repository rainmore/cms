package com.rainmore.cms.modules.core.data

import java.io.Serializable
import org.springframework.http.{HttpStatus, ResponseEntity}

object ApiResponse {

    def build[T <: Serializable](status: HttpStatus, body: ApiResponseBody[T]): ApiResponse[T] = {
        ApiResponse(ResponseEntity.status(status).body(body))
    }

    def build[T <: Serializable](status: HttpStatus, messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = {
        build(status, ApiResponseBody[T](None, messages))
    }

    def ok[T <: Serializable](messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = build(HttpStatus.OK, messages)

    def badRequest[T <: Serializable](messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = build(HttpStatus.BAD_REQUEST, messages)

    def forbidden[T <: Serializable](messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = build(HttpStatus.FORBIDDEN, messages)

    def noFound[T <: Serializable](messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = build(HttpStatus.NOT_FOUND, messages)

    def unprocessableEntity[T <: Serializable](messages: ApiMessages = new ApiMessages): ResponseEntity[ApiResponseBody[T]] = build(HttpStatus.UNPROCESSABLE_ENTITY, messages)
}

case class ApiResponse[T <: Serializable] private(entity: ResponseEntity[ApiResponseBody[T]])
    extends ResponseEntity[ApiResponseBody[T]](entity.getBody, entity.getHeaders, entity.getStatusCode) {

    val body: ApiResponseBody[T] = entity.getBody
}

object ApiResponseBody {

    def apply[T <: Serializable](data: T): ApiResponseBody[T] = {
        ApiResponseBody(Some(data))
    }
}

sealed case class ApiResponseBody[T <: Serializable]
(
    data: Option[T] = None,
    messages: ApiMessages = new ApiMessages
)

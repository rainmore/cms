package com.rainmore.modules.core.data

import com.rainmore.BaseSpec
import com.rainmore.cms.modules.core.data.{ApiResponse, FlashMessage}
import com.rainmore.cms.modules.core.json.JsonService

class ApiResponseSpec extends BaseSpec {

    private val jsonService = ctx.getBean(classOf[JsonService])

    describe(classOf[ApiResponse[_]].getCanonicalName) {
        describe("getEntityResponse()") {
            it("should generate EntityResponse with data as `null`") {
                val apiResponseBody1 = ApiResponse.ok()

                val apiResponseBody1Body = jsonService.toJson(apiResponseBody1.getBody)

                apiResponseBody1Body.contains("\"data\" : null") shouldBe true

                val apiResponseBody2 = ApiResponse.ok()
                apiResponseBody2.getBody.messages.add(FlashMessage.info("Test info message 1"))
                apiResponseBody2.getBody.messages.add(FlashMessage.error("Test error message 1"))

                val apiResponseBody2Body = jsonService.toJson(apiResponseBody2.getBody)

                apiResponseBody2Body.contains("\"data\" : null") shouldBe true
                apiResponseBody2Body.contains("Test info message 1") shouldBe true
                apiResponseBody2Body.contains("Test error message 1") shouldBe true
            }
        }

    }
}

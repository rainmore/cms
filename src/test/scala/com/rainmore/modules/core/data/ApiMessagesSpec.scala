package com.rainmore.modules.core.data

import com.rainmore.BaseSpec
import com.rainmore.cms.modules.core.data.{FlashMessage, ApiMessages, FlashMessageKind}
import com.rainmore.cms.modules.core.json.JsonService

class ApiMessagesSpec extends BaseSpec {

    private val jsonService = ctx.getBean(classOf[JsonService])

    describe(classOf[ApiMessages].getCanonicalName) {
        describe("add() and reset()") {
            it("should work as expected") {
                Given("a new flash messages")
                val flashMessages = new ApiMessages

                flashMessages.isEmpty shouldBe true
                flashMessages.nonEmpty shouldBe false

                When("adding messages")
                flashMessages.add(FlashMessage.error("test error message 1"))
                flashMessages.add(FlashMessage.error("test error message 2"))
                flashMessages.add(FlashMessage.warning("test warning message 1"))
                flashMessages.add(FlashMessage.warning("test warning message 2"))
                flashMessages.add(FlashMessage.success("test success message 1"))
                flashMessages.add(FlashMessage.success("test success message 2"))
                flashMessages.add(FlashMessage.info("test info message 1"))
                flashMessages.add(FlashMessage.info("test info message 2"))

                flashMessages.isEmpty shouldBe false
                flashMessages.nonEmpty shouldBe true

                Then("reset")
                flashMessages.reset
                flashMessages.isEmpty shouldBe true
                flashMessages.nonEmpty shouldBe false
            }
        }

        describe("getMessagesMap()") {
            it("should return expected map") {

                Given("a new flash messages")
                val flashMessages = new ApiMessages

                val emptyMap = flashMessages.toJsonMap
                emptyMap.isEmpty shouldBe true

                flashMessages.add(FlashMessage.error("test error message 1"))
                flashMessages.add(FlashMessage.error("test error message 2"))
                flashMessages.add(FlashMessage.error("test error message 3"))
                flashMessages.add(FlashMessage.warning("test warning message 1"))
                flashMessages.add(FlashMessage.warning("test warning message 2"))
                flashMessages.add(FlashMessage.success("test success message 1"))
                flashMessages.add(FlashMessage.success("test success message 2"))
                flashMessages.add(FlashMessage.info("test info message 1"))
                flashMessages.add(FlashMessage.info("test info message 2"))

                val map = flashMessages.toJsonMap
                map.nonEmpty shouldBe true
                map.contains(FlashMessageKind.Error) shouldBe true
                map(FlashMessageKind.Error).size shouldBe 3
                map.contains(FlashMessageKind.Warning) shouldBe true
                map(FlashMessageKind.Warning).size shouldBe 2
                map.contains(FlashMessageKind.Info) shouldBe true
                map(FlashMessageKind.Info).size shouldBe 2
                map.contains(FlashMessageKind.Success) shouldBe true
                map(FlashMessageKind.Success).size shouldBe 2

                val json = jsonService.toJson(flashMessages)
                Option(json).isDefined shouldBe true

                val json1 = jsonService.toJson(flashMessages.getMessages)
                Option(json1).isDefined shouldBe true
            }
        }
    }

}

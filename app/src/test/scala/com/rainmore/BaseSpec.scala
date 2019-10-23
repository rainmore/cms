package com.rainmore

import com.rainmore.config.AppCtx
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen, Matchers}
import org.springframework.context.ApplicationContext

trait BaseSpec extends org.scalatest.FunSpec
    with WithAppCtx
    with Matchers
    with GivenWhenThen
    with BeforeAndAfterEach
    with BeforeAndAfterAll
    with LazyLogging {

}

trait WithAppCtx { this: BaseSpec =>

    protected lazy val ctx: ApplicationContext = AppCtx.application

}

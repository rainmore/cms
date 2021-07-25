package com.rainmore

import com.rainmore.config.AppCtx
import com.typesafe.scalalogging.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen, Matchers}
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringRunner])
abstract class BaseSpec extends org.scalatest.FunSpec
    with Matchers
    with GivenWhenThen
    with BeforeAndAfterEach
    with BeforeAndAfterAll
    with LazyLogging {

    protected lazy val ctx: ApplicationContext = AppCtx.application

    @Transactional
    def withTransaction[T](action: => T): T = action

}


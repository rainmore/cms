package com.rainmore.cms.configs.apis

import java.util.{List => JList}

import org.springframework.data.domain.PageRequest

import scala.jdk.CollectionConverters._

object PaginationConfig {
    private val PageSize20 = 20
    private val PageSize40 = 40
    private val PageSize80 = 80

    private val DefaultPageSize = PageSize40
    private val DefaultPageNumber = 0

    private val pages = Seq[Integer](PageSize20, PageSize40, PageSize80)

    def defaultPageRequest: PageRequest = PageRequest.of(DefaultPageNumber, DefaultPageSize)

    def apply(): PaginationConfig = {
        new PaginationConfig(pages)
    }
}

case class PaginationConfig
(
    pages: Seq[Integer]
) {
    val defaultPage: Integer = pages.head

    val pageList: JList[Integer] = pages.asJava

    def sanitize(page: Integer): Integer = {
        if (pages.contains(page)) page
        else defaultPage
    }
}

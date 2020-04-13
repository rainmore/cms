package com.rainmore.cms.modules.core.data

import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.QPageRequest

object PageableUtils {

    val DefaultSize: Int = 40

    def orderBy(orders: OrderSpecifier[_ <: Comparable[_]]*)(pageable: Pageable): Pageable = {
        new QPageRequest(pageable.getPageNumber, pageable.getPageSize, orders: _*)
    }

}

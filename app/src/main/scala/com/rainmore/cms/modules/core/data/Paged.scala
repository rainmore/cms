package com.rainmore.cms.modules.core.data

import com.rainmore.cms.models.core.Model
import org.springframework.core.convert.converter.Converter
import org.springframework.data.domain.Page

case class Paged[M <: Model](page: Page[M]) {

    def convert[D <: Serializable](converter: Converter[M, D]): Page[D] = {
        page.map(converter.convert(_)).asInstanceOf[Page[D]]
    }
}

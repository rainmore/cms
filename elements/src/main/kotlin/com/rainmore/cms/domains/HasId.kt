package com.rainmore.cms.domains

import java.io.Serializable

interface HasId<ID : Serializable> {
    var id: ID?
}
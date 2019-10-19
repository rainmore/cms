package com.rainmore.cms.modules

import com.rainmore.cms.models.Model
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait BaseRepository[M <: Model, ID <: java.io.Serializable]
    extends MongoRepository[M, ID]
package com.rainmore.cms.modules

import com.rainmore.cms.models.Model
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
trait BaseRepository[M <: Model, ID <: Serializable] extends MongoRepository[M, ID]

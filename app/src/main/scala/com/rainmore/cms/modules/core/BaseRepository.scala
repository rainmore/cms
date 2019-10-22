package com.rainmore.cms.modules.core

import java.io.Serializable

import com.rainmore.cms.models.core.Model
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait BaseRepository[M <: Model, ID <: Serializable] extends MongoRepository[M, ID]

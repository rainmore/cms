package com.rainmore.cms.modules.core.jpa

import com.querydsl.core.types.dsl.EntityPathBase
import com.rainmore.cms.domains.HasId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait BaseRepository[T <: HasId[ID], ID <: java.io.Serializable, Q <: EntityPathBase[T]]
    extends JpaRepository[T, ID]
        with QuerydslPredicateExecutor[T, ID]
        with BaseQuerydslRepositorySupport[T, ID, Q]

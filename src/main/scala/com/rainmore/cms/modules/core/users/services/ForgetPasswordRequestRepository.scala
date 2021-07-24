package com.rainmore.cms.modules.core.users.services

import com.rainmore.cms.domains.system.security.auth.{ForgetPasswordRequest, QForgetPasswordRequest}

import java.util.UUID
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait ForgetPasswordRequestRepository
    extends BaseRepository[ForgetPasswordRequest, UUID, QForgetPasswordRequest]
        with ForgetPasswordRequestRepositoryCustom

trait ForgetPasswordRequestRepositoryCustom

class ForgetPasswordRequestRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[ForgetPasswordRequest, UUID, QForgetPasswordRequest](classOf[ForgetPasswordRequest], QForgetPasswordRequest.forgetPasswordRequest)
        with ForgetPasswordRequestRepositoryCustom

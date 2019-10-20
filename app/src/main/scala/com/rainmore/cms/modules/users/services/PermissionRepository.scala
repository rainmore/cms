package com.rainmore.cms.modules.users.services

import com.rainmore.cms.models.users.Permission
import com.rainmore.cms.modules.BaseRepository

trait PermissionRepository extends BaseRepository[Permission, String]

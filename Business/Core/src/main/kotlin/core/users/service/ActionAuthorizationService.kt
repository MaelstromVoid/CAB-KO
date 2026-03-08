package core.users.service

import common.error.NotAuthorized
import core.users.entity.UserRole
import core.users.model.AuthenticatedUser
import core.users.service.data.RequestType

class ActionAuthorizationService {

    fun requestValidator(requestType: RequestType, user: AuthenticatedUser) {
        val requiredUserRole = when (requestType) {
            RequestType.ADD_PRODUCT,
            RequestType.UPDATE_PRODUCT,
            RequestType.DELETE_PRODUCT -> UserRole.ADMIN
        }

        if (!user.hasRole(requiredUserRole)) {
            throw NotAuthorized("User lacks required role: $requiredUserRole for action: $requestType")
        }
    }

}
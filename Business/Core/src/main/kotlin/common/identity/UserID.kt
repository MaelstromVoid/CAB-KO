package common.identity

import common.identity.EntityID
import java.util.UUID

data class UserID(override val id: UUID) : EntityID
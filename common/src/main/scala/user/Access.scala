package user

sealed class Access {
  object Common extends Access
  object Moder extends Access
  object Admin extends Access
}

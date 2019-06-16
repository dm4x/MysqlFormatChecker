import scala.util.matching.Regex

sealed trait Checker {
  def check(token: String, lineCounter: Long, checkingRegex: Regex): Option[String]
}

object CaseChecker extends Checker {
  override def check(token: String, lineCounter: Long, checkingRegex: Regex): Option[String] =
    checkingRegex.findFirstIn(token) match {
      case Some(x) => Option(s"reserved word `$token` in line $lineCounter in wrong case\n")
      case _ => None
    }
}

object TabsChecker extends Checker {
  override def check(token: String, lineCounter: Long, checkingRegex: Regex): Option[String] =
    checkingRegex.findFirstIn(token) match {
      case Some(x) => Option(s"wrong tabulation in line $lineCounter\n")
      case _ => None
    }
}
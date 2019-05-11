import scala.util.matching.Regex

trait Checker {
  def check(token: String, lineCounter: Long, checkingRegex: Regex): String
}

object CaseChecker extends Checker {
  override def check(token: String, lineCounter: Long, checkingRegex: Regex): String =
    checkingRegex.findFirstIn(token) match {
      case Some(x) => s"reserved word `$token` in line $lineCounter in wrong case"
      case None => ""
    }
}

object TabsChecker extends Checker {
  override def check(token: String, lineCounter: Long, checkingRegex: Regex): String =
    checkingRegex.findFirstIn(token) match {
      case Some(x) => s"wrong tabulation in line $lineCounter"
      case None => ""
    }
}
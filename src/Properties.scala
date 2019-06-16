import scala.io.{BufferedSource, Source}
import scala.sys.env
import scala.util.matching.Regex

object Properties {
  val fileToCheck = s"${env("PWD")}/test.sql"
  val bufferedSource: BufferedSource = Source.fromFile(s"${env("PWD")}/SQL_reserved_words")
  val dictionary: List[String] = bufferedSource.getLines.toList
  bufferedSource.close()

  val upperRegex: Regex = "[^A-Z]".r
  val tabSpacesRegex: Regex = "([\\ ]{4,})".r
}

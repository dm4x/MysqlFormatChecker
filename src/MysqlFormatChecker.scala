import scala.io.Source
import scala.sys.env

object MysqlFormatChecker extends App {

  val checkedFile = s"${env.get("PWD").get}/test.sql"
  val dictFile = s"${env.get("PWD").get}/SQL_reserved_words"

  val bufferedSource = Source.fromFile(dictFile)
  val dictionary = bufferedSource.getLines.toList
  bufferedSource.close()
  val UpperRegex = "[^A-Z]".r

  def sqlTokenizer(line: String): List[String] = line.replaceAll("[\\)\\(]", " ").split(" ").toList

  def isReservedWord(word: String, sqlDictionary: List[String]): Boolean = dictionary.contains(word.toUpperCase())

  var counter = 0

  for (line <- Source.fromFile(checkedFile).getLines()) {
    counter = counter + 1
    val wordsFromLine = sqlTokenizer(line).filter(isReservedWord(_, dictionary))

    wordsFromLine.filterNot(_.isEmpty).foreach {word =>
        UpperRegex.findFirstIn(word) match {
          case Some(x) => println(s"reserved word `$word` in line $counter is not in UPPERCASE")
          case None => ""
        }
      }
  }
}

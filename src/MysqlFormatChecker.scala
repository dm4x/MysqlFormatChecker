import scala.io.Source
import Properties._

object MysqlFormatChecker extends App {

  def sqlTokenizer(line: String): List[String] = line
    .replaceAll("[\\)\\(]", " ")
    .split(" ")
    .toList

  def isReservedWord(word: String, sqlDictionary: List[String]): Boolean = {
    dictionary.contains(word.toUpperCase())
  }

  def isTabbedLine(line: String): Boolean = {true}


  for (line <- Source.fromFile(fileToCheck).getLines()) {
    val currentLine = LineCounter.currentLineNumber()
    sqlTokenizer(line)
      .filter(_.nonEmpty)
      .filter(isReservedWord(_, dictionary))
      .foreach(word => print(CaseChecker.check(word, currentLine, upperRegex).getOrElse("")))

    print(
      TabsChecker.check(line.take(4), currentLine, tabSpacesRegex).getOrElse("")
    )

  }
}

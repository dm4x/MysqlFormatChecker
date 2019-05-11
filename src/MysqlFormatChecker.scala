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

//  def notEmptyPrinter[A,B,C](f: (A,B,C) => A, string: String): Unit = {
//    if (string.nonEmpty) println(f(string, _, _))
//  }

  for (line <- Source.fromFile(checkedFile).getLines()) {
    val currentLine = LineCounter.currentLineNumber()
    sqlTokenizer(line)
      .filter(isReservedWord(_, dictionary))
      .foreach( word => println(CaseChecker.check(word, currentLine, upperRegex))
      )

//    println(line.startsWith("    "))

    println(TabsChecker.check(line.take(4), currentLine, tabSpacesRegex))

  }
}

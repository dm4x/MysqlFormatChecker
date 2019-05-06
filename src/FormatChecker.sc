import scala.io.Source

val checkedFile = "/home/kiselev/workspace/Scala/MysqlFormatChecker/src/test.sql"
val dictFile = "/home/kiselev/SQL_reserved_words"

val bufferedSource = Source.fromFile(dictFile)
val dictionary = bufferedSource.getLines.toList
bufferedSource.close()
val UpperRegex = "[^A-Z]".r

def sqlTokenizer(line: String): List[String] = {
  line.replaceAll("[\\)\\(]"," ").split(" ").toList
}

def isReservedWord(word: String, sqlDictionary: List[String]): Boolean = dictionary.contains(word.toUpperCase())

for (line <- Source.fromFile(checkedFile).getLines()){
  val wordsFromLine = sqlTokenizer(line).filter(isReservedWord(_, dictionary))

  println(wordsFromLine.filterNot(_.isEmpty))

  println("--------------------------")

  wordsFromLine
    .filterNot(_.isEmpty)
    .foreach {
    UpperRegex.findFirstIn(_) match {
      case Some(x) => println(s"$Some(x) is not in UPPERCASE")
      case None => ""
    }
  }



}






object LineCounter {
  var count = 0

  def currentLineNumber(): Long = {
    count += 1
    count
  }
}

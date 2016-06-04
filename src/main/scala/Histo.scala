package ecevit

object Histo {

  def histogram(arr: Array[String]): Map[String,Int] = {
    arr.groupBy(identity).mapValues(_.size)
  }
}

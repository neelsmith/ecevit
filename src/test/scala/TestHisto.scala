package ecevit

import org.specs2.mutable._
import scala.io.Source

class  HistogramTest extends Specification {

  "A Histogram object" should {
    "map strings to counts" in {
      val fileSource = Source.fromURL(getClass.getResource("/18.12.1950.txt"))
      val tokens = Tokenizer.tokenizeFile(fileSource)
      val hist = Histo.histogram(tokens)
      hist.values must have size(103)
      hist.values.max must_== 5
      hist.values.min must_== 1
    }
   }


}

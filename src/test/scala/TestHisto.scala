package ecevit

import org.specs2.mutable._
import scala.io.Source
import java.io.File
import java.io.PrintWriter
import scala.collection.immutable.ListMap

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



   "A Histogram object" should {
     "map even a whole corpus of tokens to counts" in {
       val resDir = new File("ocr")
       val tokens = Tokenizer.tokenizeFileTree(resDir)
       val hist = Histo.histogram(tokens)
       println ("Input size (tokens): " + tokens.size)
       println ("Number values (distinct tokens): " + hist.values.size)
       println("Max occurrences: " + hist.values.max)

       val sortedByCount = hist.toSeq.sortBy(_._2).reverse

       // save top 100 in a file just for fun
       val outWriter = new PrintWriter("corpus-freqs.txt")
       //for (wd <- sortedByCount.take(100)) {
       for (wd <- sortedByCount) {
         outWriter.println(wd)
       }
       outWriter.close()

       val outFile = new File("corpus-freqs.txt")
       val wroteIt = outFile.exists()
       wroteIt === true

       //tokens.size must_== 542451
       //hist.values.size must_== 110552
     }
   }

}

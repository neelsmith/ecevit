package ecevit

import org.specs2.mutable._
import scala.io.Source
import java.io.File

class TokenizerTest extends Specification {

 "A Tokenizer" should {
   "tokenize a String to an Array" in {
     val str = "Güzel Sanatlar Birliği, 1923 de Cumhuriyetin ilânı günü tesis ettiği (Ankara resim sergisi)"
     val tokens = Tokenizer.tokenize(str)
     tokens must haveSize(13)
   }
 }

 "A Tokenizer" should {
   "tokenize a file source to a single Array" in {
     val fileSource = Source.fromURL(getClass.getResource("/18.12.1950.txt"))
     val tokens = Tokenizer.tokenizeFile(fileSource)
     //for (i <- 0 until tokens.length) {
       //println(i.toString() + " "  + tokens(i))
     //}
     tokens must have size(127)
   }
  }


   "A Tokenizer" should {
     "tokenize a file identified by string name to a single Array" in {
       val fileSource = Source.fromURL(getClass.getResource("/18.12.1950.txt"))
       val tokens = Tokenizer.tokenizeFile(fileSource)
       //for (i <- 0 until tokens.length) {
         //println(i.toString() + " "  + tokens(i))
       //}
       tokens must have size(127)
     }
    }

    "A Tokenizer" should {
      "tokenize multiple files identified by string name to a single Array" in {
        val tokens = Tokenizer.tokenizeFiles("src/test/resources/04.11.1950.txt","src/test/resources/18.12.1950.txt","src/test/resources/30.12.1950.txt","src/test/resources/24.10.1950.txt")
        //for (i <- 0 until tokens.length) {
          //println(i.toString() + " "  + tokens(i))
        //}
        tokens must have size(1721)
      }
     }

     /*

     "A Tokenizer" should {
       "tokenize files in a given directory to a single Array" in {
         val resDir = new File("src/test/resources")
         val tokens = Tokenizer.tokenizeFiles(resDir)
         //for (i <- 0 until tokens.length) {
           //println(i.toString() + " "  + tokens(i))
         //}
         tokens must have size(127)
       }
     }*/

     //"src/test/resources/4.11.1950_devamı.txt"
}

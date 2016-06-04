package ecevit

import org.specs2.mutable._
import scala.io.Source
import java.io.File

class  TreeWalkTest extends Specification {

  "A Tokenizer object" should {
    "collect files matching default extension from a given root dir" in {
      val root = new File("src/test/resources")
      val files = Tokenizer.collectFiles(root)
      files must have size(22)
    }
   }

   "A Tokenizer object" should {
     "collect files matching a given extension from a given root dir" in {
       val root = new File("src/test/resources")
       val sub = Tokenizer.collectFiles(root, ".md")
       sub must have size(1)
     }
    }



   "A Tokenizer object" should {
     "retrieve a file list from a directory by default .txt extension" in {
       val root = new File("src/test/resources")
       val files = Tokenizer.getFilesByExtension(root)
       files must have size(5)
     }
    }


    "A Tokenizer object" should {
      "retrieve a file list from a directory by specified extension" in {
        val root = new File("src/test/resources")
        val files = Tokenizer.getFilesByExtension(root, ".md")
        files must have size(1)
      }
    }
}

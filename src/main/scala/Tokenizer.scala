package ecevit

import scala.io.Source
import scala.collection.mutable.Buffer
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.ArrayBuffer

import java.io.File

object Tokenizer {
  // Tokenize on whitespace or punctuation
  def tokenize(s: String) = {
    s.split("[\\s,\\p{Punct}]+")
  }

  def tokenizeFile(fSource : Source):  Array[String] = {
    val lines = fSource.getLines
    val tokens = ArrayBuffer[String]()
    for (ln <- lines) {
      val newTokens = tokenize(ln)
      tokens ++= newTokens
    }
    tokens.filter(_ != "").toArray[String]
  }


  def tokenizeFiles(fileList : Array[File]):  Array[String] = {
    val tokens = ArrayBuffer[String]()
    for (f <- fileList) {
      try {
        val src = Source.fromFile(f, "UTF-8")
        tokens ++= tokenizeFile(src)
      } catch {
        case _ : Throwable => println("Failed on file " + f)
      }
    }
    tokens.toArray[String]
  }


  def subdirs(dir: File): Iterator[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs _)
  }

  def getFilesByExtension(dir: File, extension: String = ".txt") : Array[File] = {
    val pattn = ".*" + extension r
    val files = dir.listFiles.filter {
      _.getName() match {
        case pattn() => true
        case _ => {false}
      }
    }
    files
  }

  def collectFiles(root: File, extsn: String = ".txt"): Array[File] = {
    val files = ArrayBuffer[File]()
    files ++= Tokenizer.getFilesByExtension(root, extsn)
    for (dir <- subdirs(root)) {
      files ++= Tokenizer.getFilesByExtension(dir, extsn)
    }
    files.toArray
  }

  def tokenizeFileTree(root: File, extsn: String = ".txt"): Array[String] = {
    // collect files
    val fileList = collectFiles(root, extsn)
    tokenizeFiles(fileList)
  }

}

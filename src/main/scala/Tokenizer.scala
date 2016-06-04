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


  def tokenizeFiles(fNames : String*):  Array[String] = {
    val tokens = ArrayBuffer[String]()
    for (fName <- fNames) {
      val f = Source.fromFile(fName, "UTF-8")
      tokens ++= tokenizeFile(f)
    }
    tokens.toArray[String]
  }


  def subdirs(dir: File): Iterator[File] = {
    val allKids = dir.listFiles
    //println("Files in " + dir + ":  " + allKids.size)
    val children = dir.listFiles.filter(_.isDirectory)
    println ("Dir. children of " + dir + ": " + children)
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

/*
  def tokenizeFiles(rootDir : File):  Array[String] = {
    val tokens = ArrayBuffer[String]()
    for (fName <- fNames) {
      val f = Source.fromFile(fName, "UTF-8")
      tokens ++= tokenizeFile(f)
    }
    tokens.toArray[String]
  }*/
}

//

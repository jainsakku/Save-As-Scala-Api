
package toreplace
import java.io._
import scala.io.Source
import utils.CSVReader

object Replace extends App {
  def replace(path:String){


  val rootDir = "/Users/saksham/Downloads/TT2/"
  var fileList = getListOfFiles(rootDir)

  val srcIDFile = "/Users/saksham/Desktop/work/"+path
  for (row <- Source.fromFile(srcIDFile).getLines) {
    var IDs = CSVReader.parse(row, '"', ",".toCharArray.head, '"').getOrElse(List())
    val (charsetName, fileNames) = ("UTF8", fileList)
    for (fileHandle <- fileNames) {
      var tmpFile = new File(fileHandle.replace(".csv", ".csvtmp"))
      val w = new PrintWriter(tmpFile)
      val oldID = IDs(0).r
      Source.fromFile(fileHandle).getLines
        .map { x => oldID.replaceAllIn(x, IDs(1)) }
        .foreach(x => w.println(x))
      w.close()
      tmpFile.renameTo(new File(fileHandle))
    }
  }

}

  def getListOfFiles(dir: String): List[String] = {
    val file = new File(dir)
    file.listFiles.filter(_.isFile)
      .filterNot(_.getName.endsWith("Store"))
      .map(_.getPath).toList
  }

}

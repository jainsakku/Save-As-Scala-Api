
package toreplace
import java.io._
import java.util.UUID.randomUUID

import scala.io.Source

import utils.CSVReader
import toreplace.Replace._
object Main extends App {

  val filename = "input.txt"
  var string1=""
  var string2=""
  var ipFile=""
  var idField=0
  var oppath=""
  var path=""
  var flag=0
  for (line <- Source.fromFile(filename).getLines) {
      var temp=line.split(",")
      path=temp(0)
      idField=temp(1).toInt
      ipFile="/Users/saksham/Downloads/TT2/"+path
      oppath="/Users/saksham/Desktop/work/"+path

      var opFile = new File(oppath)
      val bw = new BufferedWriter(new FileWriter(opFile))
      flag=0
      for (row <- Source.fromFile(ipFile).getLines) {
        var cells = CSVReader.parse(row, '"', ",".toCharArray.head, '"').getOrElse(List())
        if(flag==0)
        {
          println("Ho gaya")
          bw.write(cells(idField) + "," + cells(idField))
          println(cells(idField) + "," + cells(idField))
          bw.newLine()

          flag=1

        }
        else{
                    println("Nahi gaya")

        bw.write(cells(idField) + "," + randomUUID().toString)
        bw.newLine()
      }
    //println(cells(idField) + "," + randomUUID().toString)
      }

      bw.close()

      replace(path)
      //var ipFile="/Users/saksham/Downloads/AT-3/"
      //ipFile=ipFile+line.toString()

    }

  // val ipFile = "/Users/saksham/Downloads/AT-3/Queries.csv"
  // var idField = 10

  // val opFile = new File("/Users/saksham/Desktop/Queries.csv")
  // val bw = new BufferedWriter(new FileWriter(opFile))

  // for (row <- Source.fromFile(ipFile).getLines) {
  //   var cells = CSVReader.parse(row, '"', ",".toCharArray.head, '"').getOrElse(List())
  //   bw.write(cells(idField) + "," + randomUUID().toString)
  //   bw.newLine()
  //   //println(cells(idField) + "," + randomUUID().toString)
  // }

  // bw.close()
}

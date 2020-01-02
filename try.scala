package controllers
import java.io.{BufferedReader, InputStreamReader, OutputStreamWriter}

import scala.io.Source
import java.net.{HttpURLConnection, URL}

import com.google.common.io.BaseEncoding
import javax.inject.Inject
import play.api.db.Database
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, ControllerComponents}

class test  @Inject()(controller: ControllerComponents) extends AbstractController(controller){

  def get= Action{
    var url="https://api.bitbucket.org/2.0/repositories/"
    var username="jainsaksham36b@gmail.com:Vmc1234$"



  var basicauth="Basic "+BaseEncoding.base64().encode(username.getBytes())
    //@throws(classOf[java.io.IOException])
    //@throws(classOf[java.net.SocketTimeoutException])
    val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
    connection.setRequestProperty("X-Requested-With","Curl")
    connection.setDoOutput(true)
    connection.setDoInput(true)
    connection.setRequestProperty("Content-Type","application/json")
    connection.setRequestMethod("POST")
    connection.setRequestProperty("Authorization",basicauth)
    connection.connect()


    val str: String =
      """
        |{
        | "name": "myrepo",
        | "scmId": "git",
        | "forkable": "true"
        |}
      """.stripMargin
    val jsobject: JsValue = Json.parse(str)

    var os=connection.getOutputStream

    var osw=new OutputStreamWriter(os,"UTF-8")
    os.write(jsobject.toString().getBytes("UTF-8"))
    osw.flush()
    osw.close()
    var sb=new StringBuilder()
    var br=new BufferedReader(new InputStreamReader(connection.getInputStream()))
    var line:String=null
    while((line=br.readLine())!=null)
      {
        sb.append(line+"\n")
        
      }
    br.close()
    println(line)




    connection.setConnectTimeout(5000)
    connection.setReadTimeout(5000)
    val inputStream = connection.getInputStream
    val content = Source.fromInputStream(inputStream).mkString
    if (inputStream != null) inputStream.close
    content
    Ok(content)
  }

}

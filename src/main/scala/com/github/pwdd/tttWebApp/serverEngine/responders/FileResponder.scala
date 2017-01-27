package com.github.pwdd.tttWebApp.serverEngine.responders
import java.io.{ByteArrayInputStream, FileInputStream, IOException, InputStream}
import java.nio.file.{Files, Paths}

object FileResponder extends TResponder {

  def canRespond(fullURI: String): Boolean = {
    !NewGameResponder.canRespond(fullURI) && Files.exists(Paths.get(fullURI))
  }

  def header(fullURI: String, date: String): InputStream = {
    new ByteArrayInputStream((protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
      "Content-Type: " + getContentType(fullURI) + CRLF +
      "Date: " + date + CRLF +
      CRLF).getBytes());
  }

  @throws[IOException]
  def body(fullURI: String): InputStream = {
    val requested = fullURI.toLowerCase
    requested match {
      case js if (isJSRoot(requested)) => new FileInputStream(fullURI + "/index.html")
      case _ => new FileInputStream(fullURI)
    }
  }

  private def isJSRoot(uri: String) = {
    val js = Paths.get(System.getProperty("user.dir"), "/public/js").toString.toLowerCase
    uri.toLowerCase == js || uri.toLowerCase == js + "/"
  }

  private def getContentType(uri: String): String = {
    val extension = getExtension(uri)
    extension match {
      case "pdf" => "applicatioin/pdf"
      case "js" => "text/plain"
      case "css" => "text/plain"
      case image if (isImage(extension)) => "image/" + imageExtension(extension)
      case _ => "text/html"
    }
  }

  private def getExtension(filename: String): String = {
    val list = filename.split("\\.")
    list(list.length - 1)
  }

  private def isImage(filename: String): Boolean = {
    val re = "\\.jpeg|\\.jpg\\.png".r
    !re.findFirstIn(filename).isEmpty
  }

  private def imageExtension(extension: String): String = if (extension == "png") extension else "jpeg"
}

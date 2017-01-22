package com.github.pwdd.tttWebApp.tttEngine

import java.io.{File, PrintWriter}

class WebView(val filePath: String) {
  def printMessage(message: String): Unit = {
    val printer = new PrintWriter(new File(filePath))
    printer.write(message)
    printer.close()
  }
}
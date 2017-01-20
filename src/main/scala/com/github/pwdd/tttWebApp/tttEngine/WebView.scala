package com.github.pwdd.tttWebApp.tttEngine

import java.io.{File, PrintWriter}

class WebView(val filePath: String = "public/play.html") extends com.github.pwdd.ttt.View {
  def printMessage(message: String): Unit = {
    val printer = new PrintWriter(new File(filePath))
    printer.write(message)
    printer.close()
  }

  def delay(duration: Int): Unit = Thread.sleep(duration)
}
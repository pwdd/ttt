package com.github.pwdd.tttWebApp.tttEngine

import java.io.File

import org.scalatest.FunSuite

class WebViewSuite extends FunSuite {
  private val path = "src/test/scala/com/github/pwdd/tttWebApp/tttEngine/mocks/files/foo.html"
  private val view = new WebView(path)

  test("printMessage: writes to a file") {
    view.printMessage("foo")
    val fileContent = scala.io.Source.fromFile(path).mkString
    assert(fileContent === "foo" )
    new File(path).delete
  }
}

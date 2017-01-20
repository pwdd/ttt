package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths
import scala.io.Source.{fromFile, fromInputStream}
import com.github.pwdd.tttWebApp.serverEngine.responders.NewGameResponder
import org.scalatest.FunSuite

class NewGameResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase

  test("canRespond: is true for root") {
    assert(NewGameResponder.canRespond(root))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameResponder.canRespond(root + "/play"))
  }

  test("body: returns the content of public/base.html") {
    val source = fromFile("public/base.html")
    val lines = try source.mkString finally source.close()
    val body = fromInputStream(NewGameResponder.body(root)).mkString

    assert(lines == body)
  }
}

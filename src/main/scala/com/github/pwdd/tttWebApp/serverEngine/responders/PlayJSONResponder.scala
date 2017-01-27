package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.GameSettings
import play.api.libs.json.Json

case class PlayJSONResponder(requestBody: String) extends TResponder {
  private val validMarkers = List(GameSettings.emptySpot, GameSettings.firstPlayerMarker, GameSettings.secondPlayerMarker)

  def canRespond(fullURI: String): Boolean = fullURI.toLowerCase.matches(".*/move.json/?$.*") && hasValidRequest

  def hasValidRequest: Boolean = {
    val board = formData._1

    board.length == 9 &&
    board.forall(marker => validMarkers.contains(marker)) &&
    formData._2 != -1
  }

  def header(fullURI: String, date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: application/json" + CRLF +
        CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream = {
    JSONResponseBuilder.createResponse(GameAdapter.play(formData))
  }

  def formData: (List[Symbol], Int) = {
    val json = Json.parse(requestBody)
    val board = (json \ "board").asOpt[List[String]]

    val validBoard = board match {
      case Some(value) => value.map { spot => Symbol(spot) }
      case None => List()
    }

    val spot = (json \ "spot").asOpt[Int]
    val validSpot = spot match {
      case Some(value) => value
      case None => -1
    }
    (validBoard, validSpot)
  }
}

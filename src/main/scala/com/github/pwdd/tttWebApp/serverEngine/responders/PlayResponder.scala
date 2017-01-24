package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}

import com.github.pwdd.tttcore.Board

case class PlayResponder(requestBody: String) extends TResponder {
  private val boardSize = 9
  private val boardRepresentation = Map('_ -> "-", 'x -> "x", 'o -> "o")
  private val validMarkers = List(Board.emptySpot, Board.firstPlayer, Board.secondPlayer)

  def canRespond(fullURI: String): Boolean = fullURI.toLowerCase.matches("/play/?$") && isValidRequest

  def isValidRequest: Boolean = {
    val board = formData._1
    val spot = formData._2
    board.length == 9 &&
      board.forall(marker => validMarkers.contains(marker)) &&
      spot != -1
  }

  def header(fullURI: String, date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: text/html" + CRLF +
        CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream = {
    HTMLResponseBuilder.createResponse(GameAdapter.play(formData), "public/play.html")
  }

  def formData: (List[Symbol], Int) = {
    val boardString = formMap("board")
    val spot = formMap("input").toInt
    (boardStateToGameBoard(boardString), spot)
  }

  def formMap: Map[String, String] = {
    val formList = requestBody.replaceAll("&|=", " ").split(" ")
    val keys = formList.filter(word => formList.indexOf(word) % 2 == 0)
    val values = formList.filter(word => formList.indexOf(word) %2 != 0)
    (keys zip values).toMap
  }

  def boardStateToGameBoard(boardState: String): List[Symbol] = {
    val trimmed = boardState.replaceAll("\\s+", "")
    val list = trimmed.split(",|%2C").toList
    val convert = list.map(getKeyFromValue(boardRepresentation, _))
    if (isValidBoardState(convert)) {
      convert
    } else {
      Board.newBoard(boardSize)
    }
  }

  def isValidBoardState(boardList: List[Symbol]): Boolean = {
    boardList.length == boardSize && boardList.forall(marker => validMarkers.contains(marker))
  }

  private def getKeyFromValue(map: Map[Symbol, String], value: String): Symbol = {
    var result: Symbol = null
    for ((k, v) <- map) {
      if (v == value) result = k
    }
    result
  }
}

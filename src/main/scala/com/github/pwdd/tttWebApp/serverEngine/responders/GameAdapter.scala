package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{FileInputStream, InputStream}

import com.github.pwdd.ttt.{Board, Validation}
import com.github.pwdd.ttt.player.computer.HardComputer
import com.github.pwdd.tttWebApp.tttEngine.{WebMessenger, WebView}

object GameAdapter {
  private val validation = Validation
  private val boardRepresentation = Map('_ -> "-", 'x -> "x", 'o -> "o")
  private val filePath = "public/play.html"
  private val firstPlayer = Board.firstPlayer
  private val secondPlayer = Board.secondPlayer

  def boardStateToGameBoard(boardState: String): List[Symbol] = {
    val trimmed = boardState.replaceAll("\\s+", "")
    val list = trimmed.split(",|%2C").toList
    val convert = list.map(getKeyFromValue(boardRepresentation, _))
    if (isValidBoardState(convert)) {
      convert
    } else {
      Board.newBoard(9)
    }
  }

  def isValidBoardState(boardList: List[Symbol]): Boolean = {
    boardList.count(_ != null) == 9
  }

  private def getKeyFromValue(map: Map[Symbol, String], value: String): Symbol = {
    var result: Symbol = null
    for ((k, v) <- map) {
      if (v == value) result = k
    }
    result
  }

  def play(formMap: Map[String, String]): InputStream = {
    val gameBoard = boardStateToGameBoard(formMap("board"))
    val nextBoard = createNextBoard(gameBoard, formMap)
    createFile(nextBoard)
  }

  def createNextBoard(board: List[Symbol], formMap: Map[String, String]): List[Symbol] = {
    def userMove = formMap("input").toInt

    if (Board.isFull(board)) board
    else {
      val userInput = userMove

      if (!validation.isValidMove(board, userInput)) board
      else {
        val tempBoard = Board.move(board, firstPlayer, userInput)
        if (Board.isFull(tempBoard)) tempBoard
        else computerMove(tempBoard)
      }
    }
  }

  private def computerMove(board: List[Symbol]): List[Symbol] = {
    val computerMove = HardComputer(secondPlayer).getSpot(board)
    Board.move(board, secondPlayer, computerMove)
  }

  private def createFile(board: List[Symbol]) = {
    val message = WebMessenger.strBoard(board)
    new WebView().printMessage(message)
    new FileInputStream(filePath)
  }
}

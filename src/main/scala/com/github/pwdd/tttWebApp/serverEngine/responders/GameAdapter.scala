package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{FileInputStream, InputStream}

import com.github.pwdd.ttt.{Board, EvalGame, Validation}
import com.github.pwdd.ttt.player.computer.HardComputer
import com.github.pwdd.tttWebApp.tttEngine.{WebMessenger, WebView}

object GameAdapter {
  private val validation = Validation
  private val boardRepresentation = Map('_ -> "-", 'x -> "x", 'o -> "o")
  private val firstPlayer = Board.firstPlayer
  private val boardSize = 9
  private val computerPlayer = HardComputer(Board.secondPlayer)

  def newGame(filePath: String): InputStream = {
    val newBoard = Board.newBoard(boardSize)
    createFile(newBoard, "public/base.html")
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
    boardList.count(_ != null) == boardSize
  }

  private def getKeyFromValue(map: Map[Symbol, String], value: String): Symbol = {
    var result: Symbol = null
    for ((k, v) <- map) {
      if (v == value) result = k
    }
    result
  }

  def play(formMap: Map[String, String], filePath: String = "public/play.html"): InputStream = {
    val gameBoard = boardStateToGameBoard(formMap("board"))
    val nextBoard = createNextBoard(gameBoard, formMap)
    createFile(nextBoard, filePath)
  }

  def createNextBoard(board: List[Symbol], formMap: Map[String, String]): List[Symbol] = {
    def userMove = formMap("input").toInt

    if (EvalGame.gameOver(board)) board
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
    val computerMove = computerPlayer.getSpot(board)
    Board.move(board, computerPlayer.marker, computerMove)
  }

  private def createFile(board: List[Symbol], filePath: String) = {
    val message = WebMessenger.strBoard(board)
    new WebView(filePath).printMessage(message)
    new FileInputStream(filePath)
  }
}

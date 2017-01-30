package com.github.pwdd.tttWebApp.serverEngine.responders

import com.github.pwdd.tttWebApp.tttEngine.{GameData, GameSettings, WebMessenger}
import com.github.pwdd.tttcore.{EvalGame, Validation}
import com.github.pwdd.tttcore.player.computer.HardComputer

object GameAdapter {
  private val validation = Validation
  private val evalGame = EvalGame
  private val firstPlayer = GameSettings.firstPlayerMarker
  private val boardSize = 9
  private val computerPlayer = HardComputer(GameSettings.secondPlayerMarker)
  private val messageStart = "<h2>"
  private val messageEnd = "</h2>"

  def newGame: List[Symbol] = GameSettings.board.newBoard(boardSize)

  def play(formData: (List[Symbol], Int)): GameData = {
    val nextBoard = createNextBoard(formData._1, formData._2)
    GameData(nextBoard, finalMsg(nextBoard))
  }

  def finalMsg(board: List[Symbol]): String = board match {
    case b if evalGame.isDraw(b) => messageStart + WebMessenger.draw + messageEnd
    case _ => messageStart + WebMessenger.win(evalGame.winnerMarker(board), evalGame.winCombo(board)) + messageEnd
  }

  def createNextBoard(board: List[Symbol], spot: Int): List[Symbol] = {

    if (evalGame.gameOver(board)) board
    else {

      if (!validation.isValidMove(board, spot)) board
      else {
        val tempBoard = GameSettings.board.move(board, firstPlayer, spot)
        if (GameSettings.board.isFull(tempBoard)) tempBoard
        else computerMove(tempBoard)
      }
    }
  }

  private def computerMove(board: List[Symbol]): List[Symbol] = {
    val computerMove = computerPlayer.getSpot(board)
    GameSettings.board.move(board, computerPlayer.marker, computerMove)
  }
}

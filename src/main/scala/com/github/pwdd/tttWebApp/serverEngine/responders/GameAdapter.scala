package com.github.pwdd.tttWebApp.serverEngine.responders

import com.github.pwdd.tttcore.{Board, EvalGame, Validation}
import com.github.pwdd.tttcore.player.computer.HardComputer

object GameAdapter {
  private val validation = Validation
  private val firstPlayer = Board.firstPlayer
  private val boardSize = 9
  private val computerPlayer = HardComputer(Board.secondPlayer)

  def newGame: List[Symbol] = Board.newBoard(boardSize)

  def play(formData: (List[Symbol], Int)): List[Symbol] = {
    createNextBoard(formData._1, formData._2)
  }

  def createNextBoard(board: List[Symbol], spot: Int): List[Symbol] = {

    if (EvalGame.gameOver(board)) board
    else {

      if (!validation.isValidMove(board, spot)) board
      else {
        val tempBoard = Board.move(board, firstPlayer, spot)
        if (Board.isFull(tempBoard)) tempBoard
        else computerMove(tempBoard)
      }
    }
  }

  private def computerMove(board: List[Symbol]): List[Symbol] = {
    val computerMove = computerPlayer.getSpot(board)
    Board.move(board, computerPlayer.marker, computerMove)
  }
}

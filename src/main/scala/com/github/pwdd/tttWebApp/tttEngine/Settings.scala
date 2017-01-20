package com.github.pwdd.tttWebApp.tttEngine
import com.github.pwdd.ttt.Board
import com.github.pwdd.ttt.messenger.Messenger
import com.github.pwdd.ttt.player.Player
import com.github.pwdd.ttt.player.computer.HardComputer

object Settings extends com.github.pwdd.ttt.Settings {
  private val firstPlayer = Board.firstPlayer
  private val secondPlayer = Board.secondPlayer

  val messenger: Messenger =  WebMessenger

  def gameType(): String = "2"

  def defineComputerLevel(marker: Symbol, isFirstPlayer: Boolean): Player = HardComputer(marker)

  def getOpponentType(gameType: String): Player = defineComputerLevel(secondPlayer, false)

  def getFirstPlayer(gameType: String): Player = ???
    //User(firstPlayer, messenger, new WebView(), WebPrompt)

  def getBoardDimension: Int = 3
}

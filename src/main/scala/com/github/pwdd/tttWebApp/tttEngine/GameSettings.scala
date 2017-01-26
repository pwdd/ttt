package com.github.pwdd.tttWebApp.tttEngine

object GameSettings {
  val board = com.github.pwdd.tttcore.Board
  val settings = com.github.pwdd.tttcore.Settings
  val firstPlayerMarker = settings.firstPlayer
  val secondPlayerMarker = settings.secondPlayer
  val emptySpot = board.emptySpot
}
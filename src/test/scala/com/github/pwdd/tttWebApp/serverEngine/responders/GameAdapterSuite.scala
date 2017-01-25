package com.github.pwdd.tttWebApp.serverEngine.responders

import com.github.pwdd.tttcore.Board
import org.scalatest.FunSuite

class GameAdapterSuite extends FunSuite {
  test("createNextBoard: returns the same board if it is full") {
    val board = List(
      'x, 'x, 'x,
      'x, 'x, 'x,
      'x, 'x, 'x)
    assert(GameAdapter.createNextBoard(board, 0) === board)
  }

  test("createNextBoard: returns a board with only user move") {
    val board = List(
      'x, 'x, 'o,
      'o, 'o, 'x,
      'x, 'o, '_
    )
    val newBoard = List(
      'x, 'x, 'o,
      'o, 'o, 'x,
      'x, 'o, 'x
    )
    assert(GameAdapter.createNextBoard(board, 8) === newBoard)
  }

  test("createNextBoard: returns a board with user move and computer move") {
    val board = List(
      '_, '_, '_,
      '_, '_, '_,
      '_, '_, '_
    )
    val newBoard = List(
      'x, '_, '_,
      '_, 'o, '_,
      '_, '_, '_
    )
    assert(GameAdapter.createNextBoard(board, 0) === newBoard)
  }
}

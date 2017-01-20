package com.github.pwdd.tttWebApp.serverEngine.responders

import com.github.pwdd.ttt.Board
import org.scalatest.FunSuite

class GameAdapterSuite extends FunSuite {
  test("boardStateToGameBoard: can represent a new board") {
    assert(GameAdapter.boardStateToGameBoard("-,-,-,-,-,-,-,-,-") === Board.newBoard(9))
  }

  test("boardStateToGameBoard: correctly converts empty spots and player markers") {
    assert(GameAdapter.boardStateToGameBoard("-,x,o,-,-,-,-,-,-") === List('_, 'x, 'o, '_, '_, '_, '_, '_, '_))
  }

  test("boardStateToGameBoard: returns a new board if state is not valid") {
    assert(GameAdapter.boardStateToGameBoard("A, B, C") === List('_, '_, '_, '_, '_, '_, '_, '_, '_))
  }

  test("createNextBoard: returns the same board if it is full") {
    val board = List(
      'x, 'x, 'x,
      'x, 'x, 'x,
      'x, 'x, 'x)
    val map = Map(("input", "0"))
    assert(GameAdapter.createNextBoard(board, map) === board)
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
    val map = Map(("input", "8"))
    assert(GameAdapter.createNextBoard(board, map) === newBoard)
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
    val map = Map(("input", "0"))
    assert(GameAdapter.createNextBoard(board, map) === newBoard)
  }
}

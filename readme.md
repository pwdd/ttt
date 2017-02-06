# Tic Tac Toe

[![Build Status](https://travis-ci.org/pwdd/ttt.svg?branch=master)](https://travis-ci.org/pwdd/ttt)

## About

User goes first; unbeatable computer plays second

## Demo:
- http://ttt-app.herokuapp.com
- http://ttt-app.herokuapp.com/js

## The project uses:

- Scala 2.12.1
- SBT 0.13.13

## Tests

Running the tests: `sbt test`

## Play

Starting the server:

- `sbt run`

Games addresses:

- Root (`/`)
- `/js`

## JSON API

The JSON object contains a `"board"` (array of strings), and `"message"` (string declaring the final state of the game)

- `/new.json` - generates a new game

```shell
$ curl -i http://ttt-app.herokuapp.com/new.json

HTTP/1.1 200 OK
// ...

{"board":["_","_","_","_","_","_","_","_","_"],"message":""}
```

- `/move.json` - returns the new state of the game.

```shell
$ curl -i -H "Content-Type: application/json" -X POST \
-d '{"board":["o", "o", "_", "x", "x", "_", "_","_", "_"],"spot":6}' \
http://ttt-app.herokuapp.com/move.json

HTTP/1.1 200 OK
// ...

{"board":["o","o","o","x","x","_","x","_","_"],"message":"Player 'o' won"}
```

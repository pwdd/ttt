var $ajax = require('./ajax.js');
var writer = require('./writer.js');

document.addEventListener("DOMContentLoaded", function (event) {

  $ajax.sendGETRequest("/new.json", writer.htmlGame);

  window.addEventListener("submit", function (event) {
    event.preventDefault();
    sendPOSTRequest(event);
  });

  document.getElementById("restart").addEventListener("click", function (event) {
    event.preventDefault();
    $ajax.sendGETRequest("/new.json", writer.htmlGame);
  })
});

var sendPOSTRequest = function (event) {
  var board = JSON.stringify(event.target.board.value.split(","));
  var spot = event.target.spot.value;
  var data = '{"board":' + board + ',"spot":' + spot + "}";

  $ajax.sendPOSTRequest("/move.json", writer.htmlGame, data);
}

module.exports = sendPOSTRequest;


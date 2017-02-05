require('./ajax.js');
var writer = require('./writer.js');

console.log(require('./writer.js'));
console.log(require('./writer.js').htmlGame);

document.addEventListener("DOMContentLoaded", function (event) {

  $ajax.sendGETRequest("/new.json", writer.htmlGame);

  window.addEventListener("submit", function (event) {
    event.preventDefault();
    var board = JSON.stringify(event.target.board.value.split(","));
    var spot = event.target.spot.value;
    var data = '{"board":' + board + ',"spot":' + spot + "}";
    $ajax.sendPOSTRequest("/move.json", writer.htmlGame, data);
  });

  document.getElementById("restart").addEventListener("click", function (event) {
    event.preventDefault();
    $ajax.sendGETRequest("/new.json", writer.htmlGame);
  })
});

!function(t){function e(o){if(n[o])return n[o].exports;var r=n[o]={i:o,l:!1,exports:{}};return t[o].call(r.exports,r,r.exports,e),r.l=!0,r.exports}var n={};return e.m=t,e.c=n,e.i=function(t){return t},e.d=function(t,n,o){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:o})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=3)}([function(t,e,n){(function(e){function n(){return window.XMLHttpRequest?new XMLHttpRequest:(e.alert("Ajax is not supported"),null)}function o(t,e){t.readyState==u&&t.status==i&&e(JSON.parse(t.responseText))}var r={},u=4,i=200;r.sendGETRequest=function(t,e){var r=n();r.onreadystatechange=function(){o(r,e)},r.open("GET",t,!0),r.send(null)},r.sendPOSTRequest=function(t,e,r){var u=n();u.onreadystatechange=function(){o(u,e)},u.open("POST",t,!0),u.setRequestHeader("Content-Type","application/json"),u.send(r)},t.exports=r}).call(e,n(2))},function(t,e){var n=function(t,e){var n=document.getElementById(t);n.innerHTML=e},o=function(t){for(var e=t.board,n='<form method="post" action="" style="display: inline">',o='<input type="hidden" name="board" value="'+e.toString()+'">',r=[2,5,8],u="",i=function(t){return r.indexOf(t)!==-1?"</form><br>":"</form>"},a=0;a<e.length;a++)u+=n,u+=o,u+='<input type="hidden" value="'+a+'" name="spot">',u+='<input id="spot-'+a+'" type="submit" value="'+e[a]+'">',u+=i(a);return u},r=function(t){n("main",o(t)),n("message",t.message)};t.exports={htmlGame:r,insertHTML:n,boardString:o}},function(t,e){var n;n=function(){return this}();try{n=n||Function("return this")()||(0,eval)("this")}catch(t){"object"==typeof window&&(n=window)}t.exports=n},function(t,e,n){var o=n(0),r=n(1);document.addEventListener("DOMContentLoaded",function(t){o.sendGETRequest("/new.json",r.htmlGame),window.addEventListener("submit",function(t){t.preventDefault();var e=JSON.stringify(t.target.board.value.split(",")),n=t.target.spot.value,u='{"board":'+e+',"spot":'+n+"}";o.sendPOSTRequest("/move.json",r.htmlGame,u)}),document.getElementById("restart").addEventListener("click",function(t){t.preventDefault(),o.sendGETRequest("/new.json",r.htmlGame)})})}]);
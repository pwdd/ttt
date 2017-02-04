/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.l = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// identity function for calling harmony imports with the correct context
/******/ 	__webpack_require__.i = function(value) { return value; };

/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};

/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};

/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 2);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports) {

var ajax = (function (global) {
  var ajax = {}

  var READY = 4;
  var OK = 200;

  function getRequestObject() {
    if (window.XMLHttpRequest) {
      return (new XMLHttpRequest());
    } else {
      global.alert("Ajax is not supported");
      return (null);
    }
  }

  ajax.sendGETRequest = function(requestUrl, responseHandler) {
    var request = getRequestObject();

    request.onreadystatechange = function() {
      handleResponse(request, responseHandler);
    };

    request.open("GET", requestUrl, true);
    request.send(null);
  };

  ajax.sendPOSTRequest = function(requestUrl, responseHandler, data) {
    var request = getRequestObject();

    request.onreadystatechange = function() {
      handleResponse(request, responseHandler);
    };

    request.open("POST", requestUrl, true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(data);
  };

  function handleResponse(request, responseHandler) {
    if ((request.readyState == READY) && (request.status == OK)) {
      responseHandler(JSON.parse(request.responseText));
    }
  }

  global.$ajax = ajax;

})(window);

module.exports = ajax;


/***/ }),
/* 1 */
/***/ (function(module, exports) {

var writer = (function (global) {

  var writer = {}

  writer.htmlGame = function(json) {
    writer.insertHTML("main", writer.boardString(json));
    writer.insertHTML("message", json["message"]);
  };

  writer.insertHTML = function(selector, html) {
    var target = document.getElementById(selector);
    target.innerHTML = html
  };

  writer.boardString = function(jsonData) {
    var board = jsonData["board"];
    var formOpen = "<form method=\"post\" action=\"\" style=\"display: inline\">";
    var hiddenBoard = "<input type=\"hidden\" name=\"board\" value=\"" + board.toString() + "\">";
    var indexBreaks = [2, 5, 8]
    var finalForm = "";

    var formClose = function(index) {
      if (indexBreaks.indexOf(index) !== -1) {
        return "</form><br>";
      } else {
        return "</form>";
      }
    }

    for (var i = 0; i < board.length; i++) {
      finalForm += formOpen;
      finalForm += hiddenBoard;
      finalForm += "<input type=\"hidden\" value=\"" + i + "\" name=\"spot\">";
      finalForm += "<input id=\"spot-" + i + "\" type=\"submit\" value=\"" + board[i] + "\">";
      finalForm += formClose(i);
    }
    return finalForm;
  };

  global.$writer = writer;

})(window);

module.exports = writer;



/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(0);
__webpack_require__(1);

document.addEventListener("DOMContentLoaded", function (event) {

  $ajax.sendGETRequest("/new.json", $writer.htmlGame);

  window.addEventListener("submit", function (event) {
    event.preventDefault();
    var board = JSON.stringify(event.target.board.value.split(","));
    var spot = event.target.spot.value;
    var data = '{"board":' + board + ',"spot":' + spot + "}";
    $ajax.sendPOSTRequest("/move.json", $writer.htmlGame, data);
  });

  document.getElementById("restart").addEventListener("click", function (event) {
    event.preventDefault();
    $ajax.sendGETRequest("/new.json", $writer.htmlGame);
  })
});


/***/ })
/******/ ]);
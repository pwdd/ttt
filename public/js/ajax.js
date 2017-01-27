(function (global) {
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

  function handleResponse(request, responseHandler) {
    if ((request.readyState == READY) && (request.status == OK)) {
      responseHandler(JSON.parse(request.responseText));
    }
  }

  global.$ajax = ajax;

})(window);

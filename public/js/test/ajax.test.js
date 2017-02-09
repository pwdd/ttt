var $ajax = require('../src/ajax');

let open, send, setRequestHeader;

function createXHRMock() {
  open = jest.fn();

  send = jest.fn();
  setRequestHeader = jest.fn();

  var xhrMockClass = function () {
    return {
      open,
      send,
      setRequestHeader
    };
  };

  window.XMLHttpRequest = jest.fn().mockImplementation(xhrMockClass);
}

describe("sendGETRequest", () => {
  test("makes a GET request", () => {
    createXHRMock();

    $ajax.sendGETRequest("http://example.com", jest.fn);

    expect(open).toBeCalledWith('GET', 'http://example.com', true);
    expect(send).toBeCalled();
  })
})

describe("sendPOSTRequest", () => {
  var data = { "foo": "bar"  }

  test("sends a POST request", () => {
    createXHRMock();

    $ajax.sendPOSTRequest("http://example.com", jest.fn, data);

    expect(open).toBeCalledWith('POST', 'http://example.com', true);
    expect(setRequestHeader).toBeCalledWith('Content-Type', 'application/json');
    expect(send).toBeCalledWith(data);
  })
})


const $ajax = require('../src/ajax');

let open, send;

function createXHRMock() {
  open = jest.fn();

  send = jest.fn();

  const xhrMockClass = function () {
    return {
      open,
      send
    };å
};

window.XMLHttpRequest = jest.fn().mockImplementation(xhrMockClass);
}

describe("sendGETRequest", () => {
  test("makes a GET request", () => {
    createXHRMock();

    $ajax.sendGETRequest("http://example.com", true);

    expect(open).toBeCalledWith('GET', 'http://example.com', true);
    expect(send).toBeCalled();
  })
})

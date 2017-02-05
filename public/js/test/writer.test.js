const writer = require('../src/writer');

document.body.innerHTML = '<div id="main"></div><div id="message"></div>';

var json = {
  "board": ["_","_", "_", "_", "_", "_", "_", "_", "_"],
  "spot": 0,
  "message": "some message"
};

describe("boardString", () => {
  var form = writer.boardString(json);

  var boardLength = json["board"].length;

  function countOcurrenciesOf(string) {
    var re = new RegExp(string, "g");
    return (form.match(re) || []).length
  }

  test('contains a representation of the board', () => {
    expect(form).toMatch('value="_,_,_,_,_,_,_,_,_"');
  });

  test("contains the value of spot", () => {
    expect(form).toMatch('value="0"')
  });

  test("contains 9 <form> tags", () => {
    expect(countOcurrenciesOf("</form>")).toEqual(boardLength);
  });

  test("contains 3 <br> tags", () => {
    expect(countOcurrenciesOf("<br>")).toEqual(Math.sqrt(boardLength));
  });

  test("all the forms contain the value 'board'", () => {
    expect(countOcurrenciesOf("name=\"board\"")).toEqual(boardLength);
  });

  test("all the forms contain the value of spot", () => {
    expect(countOcurrenciesOf("name=\"spot\"")).toEqual(boardLength);
  })
})


describe("insertHTML", () => {

  test("changes innerHTML of a given selector", () => {
    writer.insertHTML("main", "inner content");
    expect(document.getElementById("main").innerHTML).toEqual("inner content");
  });
})

describe("htmlGame", () => {

  test("inserts forms inside #main", () => {
    writer.htmlGame(json);
    var innerMain = document.getElementById("main").innerHTML;
    expect(innerMain).toContain("</form>");
  });

  test("inserts message inside #message", () => {
    writer.htmlGame(json);
    var innerMessage = document.getElementById("message").innerHTML;
    expect(innerMessage).toContain("some message");
  })
})

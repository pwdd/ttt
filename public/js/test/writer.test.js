const writer = require('../src/writer');

describe("boardString", () => {
  var json = {
    "board": ["_","_", "_", "_", "_", "_", "_", "_", "_"],
    "spot": 0
  };

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


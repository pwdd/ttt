const writer = require('../src/writer');

test('add 1 + 2 to equal 3', () => {
  var json = {
    "board": ["_","_", "_", "_", "_", "_", "_", "_", "_"],
    "spot": 0
  };
  expect(writer.boardString(json)).toMatch("_,_,_,_,_,_,_,_,_");
});


jest.mock('../src/ajax.js');

const scripts = require('../src/script.js');

describe('sendPOSTRequest', () => {
  test("sends an ajax request on form submit", () => {

//    const ajax = require('../src/ajax.js').sendPOSTRequest;
//
//    const submit = new Event('submit');
//
//    const event = jest.fn().mockReturnValueOnce( {
//      'target': {
//        'board': {
//          'value': "_,_,_,_,_,_,_,_,_"
//        },
//        'spot': {
//          'value': "0"
//        }
//      }
//    });
//
//    const load = new Event('DOMContentLoaded');
//
//    document.dispatchEvent(load);
//
//    document.body.innerHTML =
//      '<form>' +
//      '  <input type="hidden" name="board" value="_,_,_,_,_,_,_,_,_"' +
//      '  <input type="hidden" name="spot" value="0"' +
//      '  <input type="submit" id="foo">' +
//      '</form>';
//
//
//    window.dispatchEvent(submit);
//
//    expect(jest.fn().mockImplementation(
//          scripts.sendPOSTRequest)).toBeCalled();
//
//    scripts.sendPOSTRequest(submit)
  });
})

import {errorsReducer} from "./errors";
import {errorsAdded, errorsDismissedFirst} from "../actions/errors";

describe('Errors reducer', () => {
  it('removes error', () => {
    const state = ['foo', 'bar']
    expect(errorsReducer(state, errorsDismissedFirst()))
      .toEqual(['bar'])
  })
  it('appends errors', () => {
    const state = ['foo']
    expect(errorsReducer(state, errorsAdded('baz')))
      .toEqual(['foo', 'baz'])
  })
})

import {ErrorAlert} from "./ErrorAlert";
import {render} from "@testing-library/react";
import React from "react";
import {Provider} from "react-redux";
import {createMyStore, store} from "../../redux";
import '@testing-library/jest-dom'
import {errorsAdded, errorsDismissedFirst} from "../../actions/errors"; // extends Jest with .toHaveTextContent

describe('Error Alert', () => {
  it('displays error msg', () => {
    const store = createMyStore();
    const alert = render(<Provider store={store}><ErrorAlert/></Provider>);
    store.dispatch(errorsAdded('fdsa'))
    expect(alert.queryAllByText('fdsa')).toHaveLength(1)
  })
  it('dismisses error msg', async () => {
    const store = createMyStore();
    const alert = render(<Provider store={store}><ErrorAlert/></Provider>);
    store.dispatch(errorsAdded('fdsa'))
    store.dispatch(errorsDismissedFirst())
    expect(alert.queryAllByText('fdsa')).toEqual([])
  })
})

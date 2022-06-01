import nock from 'nock'
import {render, waitFor} from "@testing-library/react";
import React from "react";
import {App} from "./App";
import {Provider} from "react-redux";
import {store} from "./redux";
import 'whatwg-fetch' // sets global.fetch
import '@testing-library/jest-dom'
import {formatAllocationRate} from "./reducers/allocations"; // extends Jest with .toHaveTextContent

describe('Llending Llama UI', () => {
  beforeEach(nock.cleanAll)

  it('works', async () => {
    nock(/./)
      .get('/api/best-rate')
      .reply(200, {name: 'foo', rate: 7})
    nock(/./).get(x => x.includes('/allocation')).reply(200, [])

    const c = render(<Provider store={store}><App/></Provider>);
    await waitFor(() => c.getByText('foo', {exact:false}))
    await new Promise((resolve) => setTimeout(resolve, 10));
    expect(c.getByTestId('allocation-c020b901')).toHaveTextContent('7.00%') // .00 because of other test
  })

  it('works with 3 decimal places', async () => {
    nock(/./)
      .get('/api/best-rate')
      .reply(200, {name: 'foo', rate: 7.168})
    nock(/./).get(x => x.includes('/allocation')).reply(200, [])

    const c = render(<Provider store={store}><App/></Provider>);
    await waitFor(() => c.getByText('foo', {exact:false}))
    await waitFor(() => c.getByText('7.17', {exact:false}))
    await new Promise((resolve) => setTimeout(resolve, 10));
    expect(c.getByTestId('allocation-c020b901')).toHaveTextContent('7.17%')
  })

  it('formats percentages with 2 decimals and percentage symbol', async () => {
    expect(formatAllocationRate(7)).toBe("7.00%");
    expect(formatAllocationRate(7.1)).toBe("7.10%");
    expect(formatAllocationRate(7.15)).toBe("7.15%");
    expect(formatAllocationRate(7.114)).toBe("7.11%");
    expect(formatAllocationRate(7.115)).toBe("7.12%");
    expect(formatAllocationRate(0.1)).toBe("0.10%");
    expect(formatAllocationRate()).toBe(undefined);
  })
})

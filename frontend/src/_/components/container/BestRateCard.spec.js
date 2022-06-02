import nock from 'nock'
import {render, waitFor} from "@testing-library/react";
import React from "react";
import {Provider} from "react-redux";
import {store} from "../../redux-store";
import 'whatwg-fetch' // sets global.fetch
import '@testing-library/jest-dom'
import {BestRateCard} from "./BestRateCard";
import {formatAllocationRate} from "../presentation";

describe('BestRateCard', () => {
  beforeEach(nock.cleanAll)

  it('fetches and displays best rate', async () => {
    let rate = 7;
    nock(/./)
      .get('/api/best-rate')
      .reply(200, {name: 'foo', rate: rate})

    const c = render(<Provider store={store}><BestRateCard /></Provider>);
    await waitFor(() => c.getByText('foo', {exact:false}))
    expect(c.getByTestId('allocation-c020b901')).toHaveTextContent(formatAllocationRate(rate))
  })

})

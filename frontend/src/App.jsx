import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {Card, CardWithHeader, InputWithLabel} from "./presentation";
import {AllocationsTable} from "./presentation/AllocationsTable";
import {errorsAdded} from "./actions/errors";
import {FEATURES} from "./features";
import {multipleTiersFetched} from "./actions/allocations";
import {BestRateCard} from "./components/BestRateCard";

export const App = () => {
  const features = useSelector(x => x.features)
  const featureMultipleTiersOn = features[FEATURES.MULTIPLE_TIERS] === "on";

  const dispatch = useDispatch()

  const [amount, setAmount] = useState(0.1);

  const allocations = useSelector(x=>x.allocations.multipleTiers)
  useEffect(() => {
    if (!featureMultipleTiersOn) { return }

    fetch(`/api/allocations?amount=${amount}`)
      .then(async x => {
        if (x.status >= 400) {throw new Error(await x.text())}
        return x
      })
      .then(x=>x.json())
      .then(x=>dispatch(multipleTiersFetched(x)))
      .catch(e => dispatch(errorsAdded(e.message)))
  }, [amount])

  return (
    <>
      <BestRateCard />
      {featureMultipleTiersOn
        ? <div className="pt-2">
          <CardWithHeader header="Calculate Allocation for Amount">
            <InputWithLabel
              name="amount"
              label="BTC Amount"
              type="number"
              value={amount}
              step="0.1"
              placeholder="Amount of BTC you want to lend"
              onChange={e => setAmount(e.target.value)}
            />
            <div className="pt-4"><AllocationsTable allocations={allocations}/></div>
          </CardWithHeader>
        </div>
        : null
      }
      <div className="pt-2">
        <Card>
          <p>WAGMI</p>
        </Card>
      </div>
    </>
  );
}

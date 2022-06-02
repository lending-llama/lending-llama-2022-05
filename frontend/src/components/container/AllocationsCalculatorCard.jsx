import {useDispatch, useSelector} from "react-redux";
import React, {useEffect, useState} from "react";
import {multipleTiersFetched} from "../../redux/actions/allocations";
import {errorsAdded} from "../../redux/actions/errors";
import {CardWithHeader} from "../presentation";
import {AmountInput} from "../presentation/AmountInput";
import {AllocationsTable} from "../presentation/AllocationsTable";

export const AllocationsCalculatorCard = () => {
  const dispatch = useDispatch()

  const [amount, setAmount] = useState(0.1);

  const allocations = useSelector(x=>x.allocations.multipleTiers)
  useEffect(() => {
    fetch(`/api/allocations?amount=${amount}`)
      .then(async x => {
        if (x.status >= 400) {throw new Error(await x.text())}
        return x
      })
      .then(x=>x.json())
      .then(x=>dispatch(multipleTiersFetched(x)))
      .catch(e => dispatch(errorsAdded(e.message)))
  }, [amount])

  return <CardWithHeader header="Calculate Allocation for Amount">
      <AmountInput
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <div className="pt-4"><AllocationsTable allocations={allocations}/></div>
    </CardWithHeader>;
}

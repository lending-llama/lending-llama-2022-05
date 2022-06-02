import {useDispatch, useSelector} from "react-redux";
import React, {useEffect, useState} from "react";
import {multipleTiersFetched} from "../../redux/actions/allocations";
import {CardWithHeader} from "../presentation";
import {AmountInput} from "../presentation/AmountInput";
import {AllocationsTable} from "../presentation/AllocationsTable";
import {fetchFromBackend} from "../../utils/fetcher";

export const AllocationsCalculatorCard = () => {
  const dispatch = useDispatch()

  const [amount, setAmount] = useState(0.1);

  const allocations = useSelector(x=>x.allocations.multipleTiers)
  useEffect(() => {
    fetchFromBackend(`/api/allocations?amount=${amount}`, dispatch, multipleTiersFetched)
  }, [amount])

  return <div className="pt-2">
    <CardWithHeader header="Calculate Allocation for Amount">
      <AmountInput
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <div className="pt-4"><AllocationsTable allocations={allocations}/></div>
    </CardWithHeader>
  </div>;
}

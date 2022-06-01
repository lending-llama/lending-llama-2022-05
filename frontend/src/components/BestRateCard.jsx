import {Card, formatAllocationRate} from "../presentation";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {bestRateFetched} from "../actions/allocations";

export function BestRateCard() {

  const dispatch = useDispatch()

  const bestAllocation = useSelector(x => x.allocations.bestRate)
  useEffect(() => {
    fetch(`/api/best-rate`)
      .then(x => x.json())
      .then(x => dispatch(bestRateFetched(x)))
  }, [])


  return (
    <div data-testid="allocation-c020b901">
      <Card>
        Best rate: {formatAllocationRate(bestAllocation.rate)} ({bestAllocation.name})
      </Card>
    </div>
  )

}

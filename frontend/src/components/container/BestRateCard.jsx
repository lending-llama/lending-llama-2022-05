import { Card, formatAllocationRate } from "../presentation";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { bestRateFetched } from "../../redux/actions/allocations";
import {myFetch} from "../../utils/format";

export function BestRateCard() {
  const dispatch = useDispatch();

  const bestAllocation = useSelector((x) => x.allocations.bestRate);
  useEffect(() => {
    myFetch(`best-rate`, dispatch, bestRateFetched);
  }, []);

  return (
    <div data-testid="allocation-c020b901">
      <Card>
        Best rate: {formatAllocationRate(bestAllocation.rate)} (
        {bestAllocation.name})
      </Card>
    </div>
  );
}



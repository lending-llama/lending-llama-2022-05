import React from "react";
import {useSelector} from "react-redux";
import {Card} from "./components/presentation";
import {FEATURES} from "./features";
import {BestRateCard} from "./components/container/BestRateCard";
import {AllocationsCalculatorCard} from "./components/container/AllocationsCalculatorCard";

export const App = () => {
  const features = useSelector(x => x.features)

  return (
    <>
      <BestRateCard />
      {features[FEATURES.MULTIPLE_TIERS] === "on"
        ? <div className="pt-2"><AllocationsCalculatorCard /></div>
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

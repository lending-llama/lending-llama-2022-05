import React from "react";
import {useSelector} from "react-redux";
import {Card} from "../presentation";
import {FEATURES} from "../../features";
import {BestRateCard} from "./BestRateCard";
import {AllocationsCalculatorCard} from "./AllocationsCalculatorCard";
import {HBox} from "./HBox";

export const App = () => {
  const features = useSelector(x => x.features)

  return (
    <HBox>
      <BestRateCard />
      {features[FEATURES.MULTIPLE_TIERS] === "on"
        ? <AllocationsCalculatorCard />
        : null
      }
      <Card>
        <p>WAGMI</p>
      </Card>
    </HBox>
  );
}

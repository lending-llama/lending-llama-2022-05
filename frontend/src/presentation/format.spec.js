import {formatAllocationRate} from "./format";

describe('#formatAllocationRate', () => {
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

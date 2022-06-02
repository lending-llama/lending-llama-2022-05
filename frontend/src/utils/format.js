import {errorsAdded} from "../redux/actions/errors";

export const formatAllocationRate = (rate) => {
  return rate && rate.toFixed(2) + "%";
}

export const myFetch = (url, dispatch, action) => {
  const baseUrl = '/api/';
  fetch(baseUrl + url)
    .then(async (x) => {
      if (x.status >= 400) {
        throw new Error(await x.text());
      }
      return x;
    })
    .then((x) => x.json())
    .then((x) => dispatch(action(x)))
    .catch((e) => dispatch(errorsAdded(e.message)));
};

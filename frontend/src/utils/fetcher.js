import {errorsAdded} from "../redux/actions/errors";

export const fetchFromBackend = (route, dispatch, method) => {
  fetch(route)
    .then(async x => {
      if (x.status >= 400) {throw new Error(await x.text())}
      return x
    })
    .then(x=>x.json())
    .then(x=>dispatch(method(x)))
    .catch(e => dispatch(errorsAdded(e.message)))
}

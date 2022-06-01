import {combineReducers, createStore} from 'redux'
import {errorsReducer} from "./reducers/errors";
import {featuresReducer} from "./reducers/features";
import {allocationsReducer} from "./reducers/allocations";

export function createMyStore() {
  return createStore(
    combineReducers({
      allocations: allocationsReducer,
      features: featuresReducer,
      errors: errorsReducer,
    }),
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  );
}

export const store = createMyStore()

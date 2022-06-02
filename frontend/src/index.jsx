import React from 'react';
import ReactDOM from 'react-dom';
import {App} from "./_/components/container/App";
import './_/features'
import './_/redux-store'
import {Provider} from "react-redux";
import {store} from "./_/redux-store";
import {AppShell} from "./_/components/container/AppShell";

const app = (
  <React.StrictMode>
    <Provider store={store}>
      <AppShell><App/></AppShell>
    </Provider>
  </React.StrictMode>
);
ReactDOM.render(app, document.getElementById('root'));

// Hot Module Replacement (HMR)
// Learn more: https://www.snowpack.dev/concepts/hot-module-replacement
if (undefined /* [snowpack] import.meta.hot */ ) {
  undefined /* [snowpack] import.meta.hot */ .accept();
}

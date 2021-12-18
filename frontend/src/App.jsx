import { React } from 'react';

import { RouterProvider, Router } from 'modules/Router';
import StoreProvider from 'store/Provider';

function App() {
  return (
    <StoreProvider>
      <RouterProvider>
        <Router />
      </RouterProvider>
    </StoreProvider>
  );
}

export default App;

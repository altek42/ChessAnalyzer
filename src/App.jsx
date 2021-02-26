import { React } from 'react';
import StoreProvider from 'store/Provider';
import { RouterProvider, Router } from 'modules/Router';

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

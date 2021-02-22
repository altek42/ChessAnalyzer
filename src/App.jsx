import { React } from 'react';
import Provider from 'store/Provider';
import SignIn from 'modules/SignIn';

function App() {
  return (
    <Provider>
      <SignIn />
    </Provider>
  );
}

export default App;

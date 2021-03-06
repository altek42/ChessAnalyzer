import React from 'react';

import PropTypes from 'prop-types';
import { Provider as ReduxProvider } from 'react-redux';

import store from './Store';

function Provider({
  children,
}) {
  return (
    <ReduxProvider store={store}>
      {children}
    </ReduxProvider>
  );
}

export default Provider;

Provider.propTypes = {
  children: PropTypes.node.isRequired,
};

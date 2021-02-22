import React from 'react';
import { Provider as ReduxProvider } from 'react-redux';
import PropTypes from 'prop-types';
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

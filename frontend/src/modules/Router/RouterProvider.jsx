import React from 'react';

import { ConnectedRouter } from 'connected-react-router';
import PropTypes from 'prop-types';

import history from 'config/routerHistory';

const RouterProvider = ({ children }) => (
  <ConnectedRouter history={history}>
    {children}
  </ConnectedRouter>
);

export default RouterProvider;

RouterProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

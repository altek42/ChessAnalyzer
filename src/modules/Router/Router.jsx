import React from 'react';
import { Route, Switch } from 'react-router-dom';
import SignIn from 'modules/SignIn/SignIn';

const Router = () => (
  <Switch>
    <Route component={SignIn} />
  </Switch>
);

export default Router;

Router.propTypes = {
};

import React from 'react';

import { Redirect, Route, Switch } from 'react-router-dom';

import NotFound from 'modules/NotFound';
import SignIn from 'modules/SignIn';

import Authorized from './AuthorizedRouter';

const Router = () => (
  <Switch>
    <Route path="/SignIn" component={SignIn} />
    <Route path="/404" component={NotFound} />
    <Authorized />
    <Redirect to="/404" />
  </Switch>
);

export default Router;

Router.propTypes = {
};

import React, { useEffect } from 'react';

import { useDispatch } from 'react-redux';
import { Redirect, Route, Switch } from 'react-router-dom';

import NotFound from 'modules/NotFound';
import SignIn from 'modules/SignIn';
import { signCheck } from 'services/authService';

import Authorized from './AuthorizedRouter';

const Router = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(signCheck());
  }, []);

  return (
    <Switch>
      <Route path="/SignIn" component={SignIn} />
      <Route path="/404" component={NotFound} />
      <Authorized />
      <Redirect to="/404" />
    </Switch>
  );
};

export default Router;

Router.propTypes = {
};

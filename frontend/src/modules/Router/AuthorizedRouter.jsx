import React, { useEffect } from 'react';

import { push } from 'connected-react-router';
import { useDispatch, useSelector } from 'react-redux';
import { Route } from 'react-router-dom';

import Home from 'modules/Home';

const AuthorizedRouter = () => {
  const isAuthorized = useSelector((state) => state.User.isAuthorized);
  const dispatch = useDispatch();
  useEffect(() => {
    if (!isAuthorized) {
      dispatch(push('/SignIn'));
    }
  }, [isAuthorized]);

  return [
    <Route key={0} exact path="/Home" component={Home} />,
    <Route key={1} exact path="/Home2" component={Home} />,
  ];
};

export default AuthorizedRouter;

AuthorizedRouter.propTypes = {
};

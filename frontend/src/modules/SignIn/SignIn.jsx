import React, { useEffect } from 'react';

import { makeStyles } from '@material-ui/core';
import { push } from 'connected-react-router';
import { useDispatch, useSelector } from 'react-redux';

import SigninForm from './form/SignInForm';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100%',
    backgroundColor: theme.palette.grey[200],
  },
}));

const SignIn = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const isAuthorized = useSelector((state) => state.User.isAuthorized);

  useEffect(() => {
    if (isAuthorized) {
      dispatch(push('/Home'));
    }
  }, [isAuthorized]);

  return (
    <div className={classes.root}>
      <SigninForm />
    </div>
  );
};

export default SignIn;

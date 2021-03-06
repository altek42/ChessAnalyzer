import React from 'react';

import { makeStyles } from '@material-ui/core';

import SigninForm from './components/SigninForm';

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
  return (
    <div className={classes.root}>
      <SigninForm />
    </div>
  );
};

export default SignIn;

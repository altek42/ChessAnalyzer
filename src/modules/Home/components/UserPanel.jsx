import React, { useCallback } from 'react';

import { Grid, Typography } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';

import { Button, Paper } from 'components';
import { signOut } from 'services/authService';

const UserPanel = () => {
  const email = useSelector((state) => state.User.email);
  const dispatch = useDispatch();
  const signOutAction = useCallback(
    () => dispatch(signOut()),
    [],
  );

  return (
    <Paper>
      <Grid container spacing={2}>
        <Grid item>
          <Typography>
            <b>
              User:
            </b>
            {' '}
            {email}
          </Typography>
        </Grid>
        <Grid item>
          <Button color="primary" variant="contained" onClick={signOutAction}>
            Sign Out
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default UserPanel;

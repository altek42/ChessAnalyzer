import {
  Button, Grid, Paper, TextField,
} from '@material-ui/core';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { signIn } from 'services/authService';

const SignIn = () => {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();

  return (
    <Paper style={{ padding: 5 }}>
      <Grid container width="400" direction="column" spacing={3}>
        <Grid item>
          <TextField variant="outlined" id="login" label="Email" value={login} onChange={(x) => setLogin(x.target.value)} />
        </Grid>
        <Grid item>
          <TextField variant="outlined" id="password" label="Password" type="password" value={password} onChange={(x) => setPassword(x.target.value)} />
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            fullWidth
            onClick={() => {
              dispatch(signIn(login, password));
            }}
          >
            Login!
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default SignIn;

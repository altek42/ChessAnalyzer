import { React, useState } from 'react';
import { firebase } from 'config';
import './App.css';
import {
  Button, Grid, Paper, TextField,
} from '@material-ui/core';
import logo from './logo.svg';

function App() {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit
          {' '}
          <code>src/App.js</code>
          {' '}
          and save to reload.
        </p>
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
                  firebase.auth().signInWithEmailAndPassword(login, password)
                    .then((res) => {
                      firebase.database().ref('test').set({
                        time: new Date().toDateString(),
                        email: login,
                      });
                    })
                    .catch((err) => {
                    });
                }}
              >
                Login!
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </header>
    </div>
  );
}

export default App;

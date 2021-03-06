import React from 'react';

import { Grid, Paper } from '@material-ui/core';

import { Button, TextInput } from 'components';

import useStyles from './useStyles';

const FormView = () => {
  const classes = useStyles();

  return (
    <Paper className={classes.root}>
      <Grid container direction="column" spacing={3}>
        <Grid item>
          <TextInput id="email" label="Email" />
        </Grid>
        <Grid item>
          <TextInput id="password" label="Password" type="password" />
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            fullWidth
            type="submit"
          >
            Login!
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};
export default FormView;

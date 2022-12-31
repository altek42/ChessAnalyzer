import React from 'react';

import { Grid } from '@material-ui/core';
import PropTypes from 'prop-types';

import { Button } from 'components';

const ActionButtons = ({
  signOutAction,
  profileUrl,
}) => (
  <Grid container spacing={1}>
    <Grid item md={6} xs={12}>
      <Button href={profileUrl} fullWidth>
        Profile
      </Button>
    </Grid>
    <Grid item md={6} xs={12}>
      <Button onClick={signOutAction} fullWidth>
        Sign Out
      </Button>
    </Grid>
  </Grid>
);

ActionButtons.propTypes = {
  signOutAction: PropTypes.func.isRequired,
  profileUrl: PropTypes.string.isRequired,
};

export default ActionButtons;

import React from 'react';

import { Grid, Typography } from '@material-ui/core';
import PropTypes from 'prop-types';

const UserInfo = ({
  email,
  name,
  username,
}) => (
  <Grid container direction="column">
    <Grid item>
      <Typography variant="h6">
        {username}
      </Typography>
    </Grid>
    <Grid item>
      <Typography variant="subtitle1">
        {name}
      </Typography>
    </Grid>
    <Grid item>
      <Typography variant="subtitle2">
        {email}
      </Typography>
    </Grid>
  </Grid>
);

UserInfo.propTypes = {
  email: PropTypes.node.isRequired,
  name: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
};

export default UserInfo;

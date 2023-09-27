import React from 'react';

import { Grid } from '@material-ui/core';
import PropTypes from 'prop-types';

import { Paper } from 'components';

import ActionButtons from './ActionButtons';
import UserInfo from './UserInfo';

const UserPanelView = ({
  signOutAction,
  avatar,
  url,
  email,
  name,
  username,
}) => (
  <Paper>
    <Grid container spacing={2}>
      <Grid item xs={4}>
        <img
          src={avatar}
          alt="profile avatar"
          width="100%"
        />
      </Grid>
      <Grid item xs={8}>
        <Grid container direction="column" justify="space-between" spacing={2}>
          <Grid item>
            <UserInfo name={name} username={username} email={email} />
          </Grid>
          <Grid item>
            <ActionButtons signOutAction={signOutAction} profileUrl={url} />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  </Paper>
);

UserPanelView.propTypes = {
  email: PropTypes.node.isRequired,
  signOutAction: PropTypes.func.isRequired,
  avatar: PropTypes.string.isRequired,
  url: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
};

export default UserPanelView;

import React from 'react';

import { Grid } from '@material-ui/core';

import { PageContainer } from 'components';

import UserPanel from './components/UserPanel';

const Home = () => (
  <PageContainer>
    <Grid container>
      <Grid item xs={4}>
        <UserPanel />
      </Grid>
    </Grid>
  </PageContainer>
);

export default Home;

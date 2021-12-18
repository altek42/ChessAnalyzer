import React from 'react';

import { Paper as PaperMUI } from '@material-ui/core';
import PropTypes from 'prop-types';

import useStyles from './useStyles';

const Paper = ({
  children,
}) => {
  const classes = useStyles();

  return (
    <PaperMUI className={classes.root}>
      {children}
    </PaperMUI>
  );
};

Paper.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Paper;

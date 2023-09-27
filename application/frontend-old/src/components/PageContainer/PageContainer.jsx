import React from 'react';

import PropTypes from 'prop-types';

import useStyles from './useStyles';

const PageContainer = ({
  children,
}) => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      {children}
    </div>
  );
};

PageContainer.propTypes = {
  children: PropTypes.node.isRequired,
};

export default PageContainer;

import { React } from 'react';

import { Button as ButtonMUI } from '@material-ui/core';

// eslint-disable-next-line
const Button = (props) => <ButtonMUI {...props} />;

Button.defaultProps = {
  color: 'primary',
  variant: 'contained',
};

export default Button;

import React from 'react';

import { TextField } from '@material-ui/core';
import { useField } from 'formik';
import PropTypes from 'prop-types';

const TextInput = ({
  id,
  label,
  type,
}) => {
  const [field, meta] = useField(id);
  const { onBlur, onChange } = field;
  const { value, error, touched } = meta;

  return (
    <TextField
      id={id}
      name={id}
      value={value}
      variant="outlined"
      label={label}
      type={type}
      onBlur={onBlur}
      onChange={onChange}
      fullWidth
      error={touched && error}
      helperText={touched && error}
    />
  );
};

TextInput.propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string,
  type: PropTypes.oneOf(['text', 'password']),
};

TextInput.defaultProps = {
  label: '',
  type: 'text',
};

export default TextInput;

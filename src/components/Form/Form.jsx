import React from 'react';

import { Formik } from 'formik';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';

const Form = ({
  children,
  onSubmit,
  validate,
  initialValues,
}) => {
  const dispatch = useDispatch();
  return (
    <Formik
      initialValues={initialValues}
      validate={validate}
      onSubmit={(values, actions) => onSubmit({ values, actions, dispatch })}
    >
      {({ handleSubmit }) => (
        <form onSubmit={handleSubmit}>
          {children}
        </form>
      )}
    </Formik>
  );
};

Form.propTypes = {
  children: PropTypes.node.isRequired,
  onSubmit: PropTypes.func,
  validate: PropTypes.func,
  initialValues: PropTypes.object,
};

Form.defaultProps = {
  onSubmit: () => {},
  validate: () => {},
  initialValues: {},
};

export default Form;

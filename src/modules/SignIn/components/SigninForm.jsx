import React from 'react';

import { Form } from 'components';

import FormView from './FormView';
import initialValues from './initialValues.json';
import onSubmit from './onSubmit';
import validate from './validate';

const SigninForm = () => (
  <Form
    initialValues={initialValues}
    onSubmit={onSubmit}
    validate={validate}
  >
    <FormView />
  </Form>
);

export default SigninForm;

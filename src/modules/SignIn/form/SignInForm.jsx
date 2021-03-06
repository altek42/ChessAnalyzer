import React from 'react';

import { Form } from 'components';

import initialValues from './initialValues.json';
import onSubmit from './onSubmit';
import SignInFormView from './SignInFormView';
import validate from './validate';

const SignInForm = () => (
  <Form
    initialValues={initialValues}
    onSubmit={onSubmit}
    validate={validate}
  >
    <SignInFormView />
  </Form>
);

export default SignInForm;

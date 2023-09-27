import { signIn } from 'services/authService';

export default ({ values, actions, dispatch }) => {
  const { email, password } = values;
  dispatch(signIn(email, password));
  actions.setSubmitting(false);
};

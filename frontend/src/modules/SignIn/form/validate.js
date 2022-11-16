import { validateRules } from 'services/validatorService';

export default (values) => validateRules(values, {
  email: ['isRequired', 'email'],
  password: ['isRequired'],
});

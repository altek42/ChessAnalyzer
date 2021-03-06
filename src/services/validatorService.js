const createErrorTrKey = (errorName) => `validator.error.${errorName}`;

const isRequired = (value) => (value ? '' : createErrorTrKey('required'));
const email = (value) => ((/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value)) ? '' : createErrorTrKey('email'));

const validatorFunc = {
  isRequired,
  email,
};

export const validate = (key, values, validators) => {
  const value = values[key];
  while (validators.length > 0) {
    const result = validatorFunc[validators.shift()](value);
    if (result) return result;
  }
  return '';
};

export const validateRules = (values, rules) => Object.keys(rules).reduce((acc, key) => {
  acc[key] = validate(key, values, rules[key]);
  return acc;
}, {});

const defaultState = {};

export default (state = defaultState, action) => {
  switch (action.type) {
    case 'AUTH_SIGNIN':
      return action.payload;
    case 'AUTH_SIGNOUT':
      return defaultState;
    default: return state;
  }
};

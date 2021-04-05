const defaultState = { };

export default (state = defaultState, action) => {
  switch (action.type) {
    case 'PLAYER_PROFILE':
      return action.payload;
    default: return state;
  }
};

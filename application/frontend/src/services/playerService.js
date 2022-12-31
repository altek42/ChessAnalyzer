import profileMock from 'mock/profile.json';

const processProfileResponse = (profile) => {
  const {
    avatar, url, name, username,
  } = profile;
  return {
    avatar, url, name, username,
  };
};

export const getProfile = () => async (dispatch) => {
  dispatch({
    type: 'PLAYER_PROFILE',
    payload: processProfileResponse(profileMock),
  });
};

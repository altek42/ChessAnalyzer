import { push } from 'connected-react-router';

import { auth } from './firebaseService';

const authUser = async (dispatch, user) => {
  if (user) {
    dispatch({
      type: 'AUTH_SIGNIN',
      payload: {
        isAuthorized: true,
        email: user.email,
        uid: user.uid,
      },
    });
  }
};

export const signIn = (login, password) => async (dispatch) => {
  const { user } = await auth.signInWithEmailAndPassword(login, password);
  await authUser(dispatch, user);
  await dispatch(push('/Home'));
};

export const signOut = () => async (dispatch) => {
  await auth.signOut();
  return dispatch({
    type: 'AUTH_SIGNOUT',
  });
};

export const signCheck = () => async (dispatch) => {
  auth.onAuthStateChanged((user) => authUser(dispatch, user));
};

export default { signIn };

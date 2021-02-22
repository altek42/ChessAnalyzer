import { auth } from './firebaseService';

export const signIn = (login, password) => async (dispatch) => {
  const { user } = await auth.signInWithEmailAndPassword(login, password);
  dispatch({
    type: 'AUTH_SIGNIN',
    payload: {
      email: user.email,
      uid: user.uid,
    },
  });
};

export const signOut = () => async (dispatch) => {
  await auth.signOut();
  return dispatch({
    type: 'AUTH_SIGNOUT',
  });
};

export default { signIn };

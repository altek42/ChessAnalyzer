import { push } from 'connected-react-router';
import { auth } from './firebaseService';

export const signIn = (login, password) => async (dispatch) => {
  const { user } = await auth.signInWithEmailAndPassword(login, password);
  await dispatch({
    type: 'AUTH_SIGNIN',
    payload: {
      isAuthorized: true,
      email: user.email,
      uid: user.uid,
    },
  });
  await dispatch(push('/Home'));
};

export const signOut = () => async (dispatch) => {
  await auth.signOut();
  return dispatch({
    type: 'AUTH_SIGNOUT',
  });
};

export default { signIn };

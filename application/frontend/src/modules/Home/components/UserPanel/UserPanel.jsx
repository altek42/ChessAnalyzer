import React, { useCallback, useEffect } from 'react';

import { useDispatch, useSelector } from 'react-redux';

import { signOut } from 'services/authService';
import { getProfile } from 'services/playerService';

import UserPanelView from './UserPanelView';

const UserPanel = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getProfile());
  }, []);
  const email = useSelector((state) => state.User.email);
  const profile = useSelector((state) => state.Profile);
  const signOutAction = useCallback(
    () => dispatch(signOut()),
    [],
  );
  const props = { email, signOutAction, ...profile };
  return (<UserPanelView {...props} />);
};

export default UserPanel;

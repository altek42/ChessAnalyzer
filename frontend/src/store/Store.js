import { connectRouter, routerMiddleware } from 'connected-react-router';
import { createStore, combineReducers, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';

import history from 'config/routerHistory';

import Profile from './user/ProfileReducer';
import User from './user/UserReducer';

const rootReducer = combineReducers({
  User,
  Profile,
  router: connectRouter(history),
});

const composeEnhancers = composeWithDevTools({
  // Specify name here, actionsBlacklist, actionsCreators and other options if needed
});

const store = createStore(rootReducer, composeEnhancers(
  applyMiddleware(
    thunk,
    routerMiddleware(history),
  ),
));
export default store;

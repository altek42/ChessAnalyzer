import { createStore, combineReducers, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';

import User from './user/UserReducer';

const rootReducer = combineReducers({
  User,
});

const composeEnhancers = composeWithDevTools({
  // Specify name here, actionsBlacklist, actionsCreators and other options if needed
});

const store = createStore(rootReducer, composeEnhancers(
  applyMiddleware(thunk),
));
export default store;

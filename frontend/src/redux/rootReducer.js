import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
const initialState = {
  userData: {
    isLogin: false,
    id: null,
  },
};
const persistConfig = {
  key: "root",

  storage,
};
const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case "LOGIN_USER":
      return { ...state, userData: action.payload };
    case "LOGOUT_USER":
      return { ...state, userData: action.payload };
    default:
      return state;
  }
};

export default persistReducer(persistConfig, rootReducer);

const initialState = {
  userData: {
    isLogin: false,
    id: null,
  },
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_USER":
      return { ...state, userData: action.payload };
    default:
      return state;
  }
};

export default rootReducer;

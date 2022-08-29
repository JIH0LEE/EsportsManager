export const login = (userData) => {
  console.log(userData);
  return { type: "LOGIN_USER", payload: userData };
};
export const logout = (userData) => {
  return { type: "LOGOUT_USER", payload: userData };
};

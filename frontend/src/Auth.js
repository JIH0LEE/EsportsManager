import React from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

function Auth(Component, option) {
  //option
  // null => 아무나 출입가능
  // true => 로그인한 유저만 출입 가능
  // false => 로그인한 유저는 출입 불가능
  const { userData } = useSelector((state) => state);
  const navigate = useNavigate();

  if (option && !userData.isLogin) {
    navigate("/login");
  }
  if (!option && userData.isLogin) {
    navigate("/");
  }

  return <Component></Component>;
}
export default Auth;

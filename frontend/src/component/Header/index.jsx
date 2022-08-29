import React from "react";
import "./style.css";
import { AiOutlineLogin, AiOutlineLogout } from "react-icons/ai";
import { useSelector, useDispatch } from "react-redux/es/exports";
import { useNavigate } from "react-router-dom";
import { logout } from "../../redux/action";

function Header() {
  const { userData } = useSelector((state) => state);
  const navbar = ["스케줄 진행하기", "팀 관리", "선수 관리", "도감"];
  const navigate = useNavigate();
  const dispatch = useDispatch();
  return (
    <div className="header">
      <div className="inner">
        <div className="logo">
          <img src="./image/logo.png" alt="log" className="image" />
        </div>
        <div className="nav-bar">
          {navbar.map((elem) => (
            <div className="nav-elem">{elem}</div>
          ))}
        </div>
        <div className="icon">
          {userData.isLogin ? (
            <AiOutlineLogout
              size="30"
              className="button"
              color="rgb(238,238,238)"
              onClick={() => {
                const userData = {
                  isLogin: false,
                  id: null,
                };
                dispatch(logout(userData));
                navigate("/");
              }}
            ></AiOutlineLogout>
          ) : (
            <AiOutlineLogin
              size="30"
              className="button"
              color="rgb(238,238,238)"
              onClick={() => {
                navigate("/login");
              }}
            ></AiOutlineLogin>
          )}
        </div>
      </div>
    </div>
  );
}
export default Header;

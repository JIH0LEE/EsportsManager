import React from "react";
import "./style.css";
import { AiOutlineLogin, AiOutlineLogout } from "react-icons/ai";
import { useSelector, useDispatch } from "react-redux/es/exports";
import { useNavigate } from "react-router-dom";
import { logout } from "../../redux/action";

function Header() {
  const { userData } = useSelector((state) => state);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  return (
    <div className="header">
      <div className="inner">
        <div
          className="logo button"
          onClick={() => {
            navigate("/");
          }}
        >
          <img src="./image/logo.png" alt="log" className="image" />
        </div>
        <div className="nav-bar">
          <div className="nav-elem button">{"스케줄 진행하기"}</div>
          <div className="nav-elem button">{"팀 관리"}</div>
          <div
            className="nav-elem button"
            onClick={() => {
              navigate("/book");
            }}
          >
            {"도감"}
          </div>
          <div className="nav-elem button">{"기록실"}</div>
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

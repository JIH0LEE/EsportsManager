import React from "react";
import "./style.css";
import { AiOutlineLogin, AiOutlineLogout } from "react-icons/ai";
import { useSelector, useDispatch } from "react-redux/es/exports";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { API_SERVER } from "../../common";
import { logout } from "../../redux/action";

function Header() {
  const { userData } = useSelector((state) => state);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const chooseRoute = () => {
    if (!userData.isLogin) {
      alert("로그인이 필요합니다");
      navigate("/login");
    }
    axios
      .get(API_SERVER + `/api/league/league-info/${userData.id}`)
      .then((res) => {
        if (!res.data.success) {
          alert("팀을 생성해주세요");
          navigate("/make-team");
          return;
        }
        navigate("/league-ready");
      });
  };
  return (
    <div className="header background">
      <div className="inner basic">
        <div
          className="logo button"
          onClick={() => {
            navigate("/");
          }}
        >
          <img src="./image/logo.png" alt="log" className="image" />
        </div>
        <div className="nav-bar">
          <div className="nav-elem button" onClick={chooseRoute}>
            {"스케줄 진행하기"}
          </div>
          <div
            className="nav-elem button"
            onClick={() => {
              navigate("/my-team");
            }}
          >
            {"팀 관리"}
          </div>
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

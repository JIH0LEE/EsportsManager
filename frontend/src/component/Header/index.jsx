import React from "react";
import "./style.css";
import { AiOutlineLogin } from "react-icons/ai";

function Header() {
  const navbar = ["스케줄 진행하기", "팀 관리", "선수 관리", "도감"];
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
          <AiOutlineLogin
            size="30"
            className="button"
            color="rgb(238,238,238)"
          ></AiOutlineLogin>
        </div>
      </div>
    </div>
  );
}
export default Header;

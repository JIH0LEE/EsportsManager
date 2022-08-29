import React from "react";
import "./style.css";
import Auth from "../../hoc/Auth";

function BanpickPage() {
  return (
    <div className="banpick-container">
      <div className="command-container">
        <div className="command">Ban</div>
        <div className="submit-button button">확인</div>
      </div>
      <div className="score-board">
        <div className="blue-board">{"내 팀"}</div>
        <div className="score">
          {1}:{0}
        </div>
        <div className="red-board">{"너 팀"}</div>
      </div>
      <div className="banpick-board">
        <div className="pick-list">
          <div className="blue-list"></div>
          <div className="champion-list"></div>
          <div className="red-list"></div>
        </div>
        <div className="ban-list"></div>
      </div>
    </div>
  );
}
export default Auth(BanpickPage, true);

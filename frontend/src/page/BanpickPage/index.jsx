import React from "react";
import "./style.css";

function BanpickPage() {
  return (
    <div className="banpick-container">
      <div className="command-container"></div>
      <div className="score-board"></div>
      <div className="banpick-board">
        <div className="ban-list"></div>
        <div className="pick-list">
          <div className="blue-list"></div>
          <div className="champion-list"></div>
          <div className="red-list"></div>
        </div>
      </div>
    </div>
  );
}
export default BanpickPage;

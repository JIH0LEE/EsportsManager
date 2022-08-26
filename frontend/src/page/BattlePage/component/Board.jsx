import React from "react";
import {
  GiStoneTower,
  GiSpikedDragonHead,
  GiImpLaugh,
  GiMoneyStack,
} from "react-icons/gi";
import "../style.css";

const Board = () => {
  return (
    <div className="board">
      <div className="team">
        <div className="team-name" style={{ color: "blue" }}>
          {"덕규팀"}
        </div>
        <div className="time">{"24:00"}</div>
        <div className="team-name" style={{ color: "red" }}>
          {"지호팀"}
        </div>
      </div>
      <div className="score">
        <div className="score-board">
          <div className="score-item">
            <GiSpikedDragonHead size="24" color="white"></GiSpikedDragonHead>
            <div className="content">{1}</div>
          </div>
          <div className="score-item">
            <GiImpLaugh size="24" color="white"></GiImpLaugh>
            <div className="content">{1}</div>
          </div>
          <div className="score-item">
            <GiStoneTower size="24" color="white"></GiStoneTower>
            <div className="content">{1}</div>
          </div>
          <div className="score-item">
            <GiMoneyStack size="24" color="white"></GiMoneyStack>
            <div className="content">{"24.2k"}</div>
          </div>
        </div>
        <div className="logo">
          <img
            src="./image/logo.png"
            alt="logo"
            style={{ width: "80px", height: "80px" }}
          ></img>
        </div>
        <div className="score-board">
          <div className="score-item">
            <div className="content">{"24.2k"}</div>
            <GiMoneyStack size="24" color="white"></GiMoneyStack>
          </div>
          <div className="score-item">
            <div className="content">{1}</div>
            <GiStoneTower size="24" color="white"></GiStoneTower>
          </div>
          <div className="score-item">
            <div className="content">{1}</div>
            <GiImpLaugh size="24" color="white"></GiImpLaugh>
          </div>
          <div className="score-item">
            <div className="content">{1}</div>
            <GiSpikedDragonHead size="24" color="white"></GiSpikedDragonHead>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Board;

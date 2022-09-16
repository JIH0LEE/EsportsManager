import React from "react";
import {
  GiStoneTower,
  GiSpikedDragonHead,
  GiImpLaugh,
  GiMoneyStack,
} from "react-icons/gi";
import "../style.css";

const Board = ({ data }) => {
  const getTotalGold = (arr) => {
    let totalGold = 0;
    for (let i = 0; i < arr.length; i++) {
      totalGold += arr[i];
    }
    let q = totalGold / 1000;
    let r = totalGold % 1000;
    return parseInt(q).toString() + "." + parseInt(r / 100).toString() + "k";
  };
  const getTowerDestroy = (arr) => {
    let count = 0;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === 0) {
        count += 1;
      }
    }
    return count;
  };

  return (
    <div className="board">
      <div className="team">
        <div className="team-name" style={{ color: "blue" }}>
          {data.blue
            ? data.gameMatch.myTeam.name
            : data.gameMatch.oppositeTeam.name}
        </div>

        <div className="team-name" style={{ color: "red" }}>
          {data.blue
            ? data.gameMatch.oppositeTeam.name
            : data.gameMatch.myTeam.name}
        </div>
      </div>
      <div className="score">
        <div className="score-board">
          <div className="score-item">
            <GiSpikedDragonHead size="24" color="white"></GiSpikedDragonHead>
            <div className="content">{0}</div>
          </div>
          <div className="score-item">
            <GiImpLaugh size="24" color="white"></GiImpLaugh>
            <div className="content">{0}</div>
          </div>
          <div className="score-item">
            <GiStoneTower size="24" color="white"></GiStoneTower>
            <div className="content">
              {getTowerDestroy(data.towerList.slice(6, 12))}
            </div>
          </div>
          <div className="score-item">
            <GiMoneyStack size="24" color="white"></GiMoneyStack>
            <div className="content">
              {getTotalGold(data.goldList.slice(0, 5))}
            </div>
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
            <div className="content">
              {getTotalGold(data.goldList.slice(5, 10))}
            </div>
            <GiMoneyStack size="24" color="white"></GiMoneyStack>
          </div>
          <div className="score-item">
            <div className="content">
              {getTowerDestroy(data.towerList.slice(0, 6))}
            </div>
            <GiStoneTower size="24" color="white"></GiStoneTower>
          </div>
          <div className="score-item">
            <div className="content">{0}</div>
            <GiImpLaugh size="24" color="white"></GiImpLaugh>
          </div>
          <div className="score-item">
            <div className="content">{0}</div>
            <GiSpikedDragonHead size="24" color="white"></GiSpikedDragonHead>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Board;

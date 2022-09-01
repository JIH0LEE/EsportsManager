import React from "react";

import "./style.css";

function PlayerCard({ player }) {
  const changePositionFormat = (value) => {
    if (value === "JUNGLE") {
      return "JNG";
    }
    if (value === "MIDDLE") {
      return "MID";
    }
    if (value === "SUPPORT") {
      return "SUP";
    }
    return value;
  };

  return (
    <div className="card-page-container">
      <div className={`card-container ${player.grade}`}>
        <img
          alt="card"
          src={`./image/${player.grade}.png`}
          className={`image ${player.grade}`}
        ></img>
        <div className="player-image-container">
          <img alt="player" className="image" src={player.image}></img>
        </div>
        <div className="player-info-container">
          <div className="grade">{player.grade}</div>
          <div className="position">
            {changePositionFormat(player.position)}
          </div>
          <div className="logo">
            <img alt="logo" src={player.baseTeam.logo} className="image"></img>
          </div>
        </div>
        <div className="player-info-container2">
          <div className="name-container">{player.nickName}</div>
          <div className="status-container">
            <div className="status-name">
              <div className="item">Ab.L</div>
              <div className="item">Ab.F</div>
              <div className="item">Ab.O</div>
            </div>
            <div className="status-value">
              <div className="item">{player.laneStatus}</div>
              <div className="item">{player.fightStatus}</div>
              <div className="item">{player.operationStatus}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default PlayerCard;

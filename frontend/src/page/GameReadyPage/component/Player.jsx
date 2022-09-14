import React from "react";
import "../style.css";

const Player = ({ player }) => {
  return (
    <>
      <div key={player.id} className="player-condition">
        <div className="player-card">
          <div className="image-container">
            <img alt="player" src={player.player.image} className="image"></img>
          </div>
          <div className="name-container">{player.player.nickName}</div>
          <div className="level-container">
            <div className="left-side">LV {player.level}</div>
            <div className="right-side">
              <div className={`exp e-${player.exp}`}></div>
            </div>
          </div>
          <div className="condition-container">
            <div>컨디션 : </div>
            <div>{player.status}</div>
          </div>
          <div className="stat-container">
            <div className="stat">
              {" "}
              <div className="name">라인전 :</div>
              <div className="num">
                <span>&nbsp;{player.player.laneStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
            <div className="stat">
              {" "}
              <div className="name">로밍 :</div>
              <div className="num">
                <span>&nbsp;{player.player.roamingStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
            <div className="stat">
              {" "}
              <div className="name">전투 :</div>
              <div className="num">
                <span>&nbsp;{player.player.fightStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
            <div className="stat">
              {" "}
              <div className="name">갱킹 :</div>
              <div className="num">
                <span>&nbsp;{player.player.gankingStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
            <div className="stat">
              {" "}
              <div className="name">운영 :</div>
              <div className="num">
                <span>&nbsp;{player.player.operationStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
            <div className="stat">
              {" "}
              <div className="name">정글링 :</div>
              <div className="num">
                <span>&nbsp;{player.player.junglingStatus}</span>
                <span style={{ color: "red" }}>+{player.level}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default Player;

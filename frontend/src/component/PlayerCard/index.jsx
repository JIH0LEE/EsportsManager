import React from "react";

import "./style.css";

function PlayerCard({ player, logo, level }) {
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
          <div className={`logo ${player.grade}`}>
            <img alt="logo" src={logo} className={`logo-image`}></img>
          </div>
        </div>
        <div className="player-info-container2">
          <div className="name-container">{player.nickName}</div>
          <div className="big-container">
            <div className="status-container">
              <div className="status-name">
                <div className="item">라인</div>
                <div className="item">전투</div>
                <div className="item">운영</div>
              </div>
              <div className="status-value">
                <div className="item">
                  {player.laneStatus}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
                <div className="item">
                  {player.fightStatus}{" "}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
                <div className="item">
                  {player.operationStatus}{" "}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
              </div>
            </div>
            <div className="status-container">
              <div className="status-name">
                <div className="item">로밍</div>
                <div className="item">갱킹</div>
                <div className="item">정글</div>
              </div>
              <div className="status-value">
                <div className="item">
                  {player.roamingStatus}{" "}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
                <div className="item">
                  {player.gankingStatus}{" "}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
                <div className="item">
                  {player.junglingStatus}{" "}
                  {level ? (
                    <>
                      {" "}
                      <span style={{ color: "red" }}>+{level}</span>
                    </>
                  ) : (
                    <></>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default PlayerCard;

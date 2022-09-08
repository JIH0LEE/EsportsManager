import React from "react";
import PlayerCard from "../../../component/PlayerCard";
import "../style.css";

const Player = ({ top, jng, mid, adc, sup, sub, logo }) => {
  return (
    <div className="player-container">
      <div className="label-container label">선수 목록</div>
      <div className="regular-container">
        <div className="position-label  basic">REGULAR</div>
        <div className="player-card-container">
          {top ? (
            <div className="player">
              <PlayerCard player={top.player} logo={logo}></PlayerCard>
            </div>
          ) : (
            <></>
          )}

          {jng ? (
            <div className="player">
              <PlayerCard player={jng.player} logo={logo}></PlayerCard>
            </div>
          ) : (
            <></>
          )}
          {mid ? (
            <div className="player">
              <PlayerCard player={mid.player} logo={logo}></PlayerCard>
            </div>
          ) : (
            <></>
          )}
          {adc ? (
            <div className="player">
              <PlayerCard player={adc.player} logo={logo}></PlayerCard>
            </div>
          ) : (
            <></>
          )}
          {sup ? (
            <div className="player">
              <PlayerCard player={sup.player} logo={logo}></PlayerCard>
            </div>
          ) : (
            <></>
          )}
        </div>
      </div>
      <div className="regular-container">
        <div className="position-label  basic">SUB</div>
        <div className="player-card-container">
          {sub.map((subPlayer, idx) => (
            <div className="player">
              <PlayerCard
                key={idx}
                player={subPlayer.player}
                logo={logo}
              ></PlayerCard>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
export default Player;

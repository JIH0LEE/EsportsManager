import React from "react";
import PlayerCard from "../../../component/PlayerCard";
import { useNavigate } from "react-router-dom";
import "../style.css";

const Player = ({ id, top, jng, mid, adc, sup, sub, logo }) => {
  const navigate = useNavigate();
  return (
    <div className="player-container">
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
        {sub.length === 0 ? (
          <div className="none-container">서브 선수가 없습니다</div>
        ) : (
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
        )}
      </div>
      <div className="button-container">
        <div
          className="submit button"
          onClick={() => {
            navigate("/entry", {
              state: {
                player: [top, jng, mid, adc, sup].concat(sub),
                logo: logo,
                id: id,
              },
            });
          }}
        >
          엔트리 변경
        </div>
      </div>
    </div>
  );
};
export default Player;

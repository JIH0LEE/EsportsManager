import React from "react";
import PlayerCard from "../../../component/PlayerCard";
import { useNavigate } from "react-router-dom";
import "../style.css";
import axios from "axios";

const Player = ({ top, jng, mid, adc, sup, sub, submitData, func }) => {
  const schedule = ["운동", "명상", "방송", "연습"];
  const arr = [top, jng, mid, adc, sup].concat(sub);
  const test = (idx, id) => {
    func(idx, id);
  };
  return (
    <>
      {arr.map((player, idx) => (
        <>
          {player ? (
            <div key={idx} className="player-condition">
              <div className="player-card">
                <div className="image-container">
                  <img
                    alt="player"
                    src={player.player.image}
                    className="image"
                  ></img>
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
                <div className="select-container">
                  {schedule.map((schedule, id) => (
                    <div
                      key={id + 1}
                      onClick={() => {
                        test(idx, id + 1);
                      }}
                      className={`schedule button2 ${
                        submitData[idx].scheduleId === id + 1 ? "clicked" : ""
                      }`}
                    >
                      {schedule}
                    </div>
                  ))}
                </div>
              </div>
            </div>
          ) : (
            <></>
          )}
        </>
      ))}
    </>
  );
};
export default Player;

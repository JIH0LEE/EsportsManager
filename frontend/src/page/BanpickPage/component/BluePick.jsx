import React from "react";
import "../style.css";

const BluePick = ({ bluePick, blueTeam }) => {
  return (
    <div className="blue-list">
      <div className="pick-item">
        <div className="name-container"> {blueTeam.top.nickName}</div>
        <div className="image-container">
          <img
            src={bluePick[0] ? bluePick[0].image : bluePick[0]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {blueTeam.jng.nickName}</div>
        <div className="image-container">
          <img
            src={bluePick[1] ? bluePick[1].image : bluePick[1]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {blueTeam.mid.nickName}</div>
        <div className="image-container">
          <img
            src={bluePick[2] ? bluePick[2].image : bluePick[2]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {blueTeam.adc.nickName}</div>
        <div className="image-container">
          <img
            src={bluePick[3] ? bluePick[3].image : bluePick[3]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {blueTeam.sup.nickName}</div>
        <div className="image-container">
          <img
            src={bluePick[4] ? bluePick[4].image : bluePick[4]}
            className="image"
          ></img>
        </div>
      </div>
    </div>
  );
};
export default BluePick;

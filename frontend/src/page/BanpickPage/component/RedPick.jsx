import React from "react";
import "../style.css";

const RedPick = ({ redPick, redTeam }) => {
  return (
    <div className="red-list">
      <div className="pick-item">
        <div className="name-container"> {redTeam.top.nickName}</div>
        <div className="image-container">
          <img
            src={redPick[0] ? redPick[0].image : redPick[0]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {redTeam.jng.nickName}</div>
        <div className="image-container">
          <img
            src={redPick[1] ? redPick[1].image : redPick[1]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {redTeam.mid.nickName}</div>
        <div className="image-container">
          <img
            src={redPick[2] ? redPick[2].image : redPick[2]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {redTeam.adc.nickName}</div>
        <div className="image-container">
          <img
            src={redPick[3] ? redPick[3].image : redPick[3]}
            className="image"
          ></img>
        </div>
      </div>
      <div className="pick-item">
        <div className="name-container"> {redTeam.sup.nickName}</div>
        <div className="image-container">
          <img
            src={redPick[4] ? redPick[4].image : redPick[4]}
            className="image"
          ></img>{" "}
        </div>
      </div>
    </div>
  );
};
export default RedPick;

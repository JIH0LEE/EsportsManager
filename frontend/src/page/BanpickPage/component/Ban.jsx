import React from "react";
import "../style.css";

const Ban = ({ blueBan, redBan }) => {
  return (
    <div className="ban-list">
      <div className="ban-left">
        <div className="ban-image-container">
          <img
            src={blueBan[0] ? blueBan[0].image : blueBan[0]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={blueBan[1] ? blueBan[1].image : blueBan[1]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={blueBan[2] ? blueBan[2].image : blueBan[2]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={blueBan[3] ? blueBan[3].image : blueBan[3]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={blueBan[4] ? blueBan[4].image : blueBan[4]}
            className="image"
          ></img>{" "}
        </div>
        ;
      </div>
      <div className="ban-right">
        <div className="ban-image-container">
          <img
            src={redBan[0] ? redBan[0].image : redBan[0]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={redBan[1] ? redBan[1].image : redBan[1]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={redBan[2] ? redBan[2].image : redBan[2]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={redBan[3] ? redBan[3].image : redBan[3]}
            className="image"
          ></img>{" "}
        </div>
        <div className="ban-image-container">
          <img
            src={redBan[4] ? redBan[4].image : redBan[4]}
            className="image"
          ></img>{" "}
        </div>
      </div>
    </div>
  );
};
export default Ban;

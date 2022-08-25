import React from "react";
import "../style.css";

const Tower = ({ color, id }) => {
  return (
    <div className="tower-container" id={`tower${id}`}>
      <div className="hp-bar">
        <div className={`hp-${color}`}></div>
        <div className={`hp-${color}`}></div>
        <div className={`hp-${color}`}></div>
        <div className={`hp-${color}`}></div>
        <div className={`hp-${color}`}></div>
      </div>
      <div className={`tower ${color}`}></div>
    </div>
  );
};
export default Tower;

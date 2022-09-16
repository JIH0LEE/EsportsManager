import React from "react";
import "../style.css";

const Tower = ({ tower, color, id }) => {
  const towerHp = () => {
    if (tower === 0) {
      return [];
    }
    if (tower <= 250) {
      return [...Array(1).keys()];
    }
    if (tower <= 500) {
      return [...Array(2).keys()];
    }
    if (tower <= 750) {
      return [...Array(3).keys()];
    }
    if (tower <= 1000) {
      return [...Array(4).keys()];
    } else {
      return [];
    }
  };
  return (
    <div className="tower-container" id={`tower${id}`}>
      <div className="hp-bar">
        {towerHp().map((hp, idx) => (
          <div key={idx} className={`hp-${color}`}></div>
        ))}
      </div>
      <div className={`tower ${color}`}></div>
    </div>
  );
};
export default Tower;

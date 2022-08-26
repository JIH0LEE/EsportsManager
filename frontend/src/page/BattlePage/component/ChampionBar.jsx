import React from "react";
import "../style.css";

const ChampionBar = () => {
  const arr = [1, 2, 3, 4, 5];
  return (
    <>
      <div className="blue-bar bar">
        {arr.map((elem) => (
          <div className="champion" key={elem}>
            <div className="name">Faker</div>
            <div className="image-container">
              <img
                src="./image/logo.png"
                alt="champion"
                style={{ width: "80px", height: "80px" }}
              ></img>
            </div>
          </div>
        ))}
      </div>
      <div className="red-bar bar">
        {arr.map((elem) => (
          <div className="champion" key={elem}>
            <div className="name">Faker</div>
            <div className="image-container">
              <img
                src="./image/logo.png"
                alt="champion"
                style={{ width: "80px", height: "80px" }}
              ></img>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};
export default ChampionBar;

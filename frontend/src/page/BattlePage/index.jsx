import React from "react";
import Tower from "./component/Tower";
import "./style.css";

function BattlePage() {
  const arr1 = [1, 2, 3, 4, 5, 6];
  const arr2 = [7, 8, 9, 10, 11, 12];
  return (
    <div className="battle-container">
      {arr1.map((elem) => (
        <Tower key={elem} id={elem} color={"blue"}></Tower>
      ))}
      {arr2.map((elem) => (
        <Tower key={elem} id={elem} color={"red"}></Tower>
      ))}
      <div className="board"></div>
      <div className="select"></div>
      <div className="blue-bar"></div>
      <div className="red-bar"></div>
    </div>
  );
}
export default BattlePage;

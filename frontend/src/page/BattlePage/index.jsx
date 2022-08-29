import React from "react";
import Tower from "./component/Tower";
import Board from "./component/Board";
import ChampionBar from "./component/ChampionBar";
import Auth from "../../hoc/Auth";

import "./style.css";

function BattlePage() {
  const arr1 = [1, 2, 3, 4, 5, 6];
  const arr2 = [7, 8, 9, 10, 11, 12];
  return (
    <>
      <div className="battle-container">
        <image
          src="./image/logo.png"
          style={{ width: "50px", height: "50px" }}
        ></image>
        {arr1.map((elem) => (
          <Tower key={elem} id={elem} color={"blue"}></Tower>
        ))}
        {arr2.map((elem) => (
          <Tower key={elem} id={elem} color={"red"}></Tower>
        ))}
        <Board></Board>
        <ChampionBar></ChampionBar>
        <div className="select"></div>
      </div>
    </>
  );
}
export default Auth(BattlePage, true);

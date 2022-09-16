import React from "react";
import "../style.css";

const ChampionBar = ({ data }) => {
  var blueTeam = [];
  var redTeam = [];
  const changeGoldFormat = (gold) => {
    let q = gold / 1000;
    let r = gold % 1000;
    return parseInt(q).toString() + "." + parseInt(r / 100).toString() + "k";
  };
  if (data.blue) {
    blueTeam = [
      data.gameMatch.myTeam.top,
      data.gameMatch.myTeam.jng,
      data.gameMatch.myTeam.mid,
      data.gameMatch.myTeam.adc,
      data.gameMatch.myTeam.sup,
    ];
    redTeam = [
      data.gameMatch.oppositeTeam.top,
      data.gameMatch.oppositeTeam.jng,
      data.gameMatch.oppositeTeam.mid,
      data.gameMatch.oppositeTeam.adc,
      data.gameMatch.oppositeTeam.sup,
    ];
  } else {
    redTeam = [
      data.gameMatch.myTeam.top,
      data.gameMatch.myTeam.jng,
      data.gameMatch.myTeam.mid,
      data.gameMatch.myTeam.adc,
      data.gameMatch.myTeam.sup,
    ];
    blueTeam = [
      data.gameMatch.oppositeTeam.top,
      data.gameMatch.oppositeTeam.jng,
      data.gameMatch.oppositeTeam.mid,
      data.gameMatch.oppositeTeam.adc,
      data.gameMatch.oppositeTeam.sup,
    ];
  }
  const arr = [1, 2, 3, 4, 5];
  return (
    <>
      <div className="blue-bar bar">
        {blueTeam.map((player, idx) => (
          <div className="champion" key={player.id}>
            <div className="name">{player.nickName}</div>
            <div className="image-container">
              <img
                src={data.champList[idx]}
                alt="champion"
                style={{ width: "80px", height: "80px" }}
              ></img>
            </div>
            <div className="gold-container basic">
              {changeGoldFormat(data.goldList[idx])}
            </div>
          </div>
        ))}
      </div>
      <div className="red-bar bar">
        {redTeam.map((player, idx) => (
          <div className="champion" key={player.id}>
            <div className="name">{player.nickName}</div>
            <div className="image-container">
              <img
                src={data.champList[idx + 5]}
                alt="champion"
                style={{ width: "80px", height: "80px" }}
              ></img>
            </div>
            <div className="gold-container basic">
              {changeGoldFormat(data.goldList[idx + 5])}
            </div>
          </div>
        ))}
      </div>
    </>
  );
};
export default ChampionBar;

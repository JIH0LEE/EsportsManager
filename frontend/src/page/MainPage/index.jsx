import React, { useState, useEffect } from "react";
import Auth from "../../hoc/Auth";
import axios from "axios";
import "./style.css";
import { API_SERVER } from "../../config";

function MainPage() {
  const [baseTeam, setBaseTeam] = useState([]);
  const [champions, setChampions] = useState([]);
  const [players, setPlayers] = useState([]);
  useEffect(() => {
    axios.get(API_SERVER + "/api/base-team").then((res) => {
      setBaseTeam(res.data);
    });
    axios.get(API_SERVER + "/api/champion").then((res) => {
      setChampions(res.data);
    });
    axios.get(API_SERVER + "/api/player").then((res) => {
      setPlayers(res.data);
    });
  }, []);

  return (
    <div className="main-container">
      <div className="team-container">
        {baseTeam.map((team, idx) => (
          <div key={idx} className="team-item">
            <div className="image-container">
              <img className="img" alt={team.name} src={team.logo}></img>
            </div>
            {team.name}
          </div>
        ))}
      </div>
      <div className="champion-container">
        {champions.map((champion, idx) => (
          <div key={idx} className="champion-item">
            <img
              alt={champion.koreanName}
              src={champion.image}
              className="img"
            ></img>
          </div>
        ))}
      </div>
      <div className="player-container">
        {players.map((player, idx) => (
          <div key={idx} className="player-item">
            <div className="image-container">
              <img
                className="img"
                alt={player.realName}
                src={player.image}
              ></img>
            </div>
            {player.nickName}
          </div>
        ))}
      </div>
    </div>
  );
}
export default Auth(MainPage, null);

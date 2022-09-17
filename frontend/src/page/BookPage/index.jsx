import React, { useState, useEffect } from "react";
import Auth from "../../hoc/Auth";
import axios from "axios";
import "./style.css";
import PlayerCard from "../../component/PlayerCard";
import { API_SERVER } from "../../config";

function BookPage() {
  const [baseTeams, setBaseTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(null);

  useEffect(() => {
    axios
      .get(API_SERVER + "/api/base-team")
      .then((res) => {
        setBaseTeams(res.data);
        setSelectedTeam(res.data[0]);
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  return (
    <div className="book-page-container background basic">
      <div className="label-container label">선수 도감</div>
      <div className="team-logo-container">
        {baseTeams.map((baseTeam, idx) => (
          <div
            key={idx}
            className={`logo-container button ${
              baseTeam === selectedTeam ? "clicked" : ""
            }`}
            onClick={() => {
              setSelectedTeam(baseTeam);
            }}
          >
            <img alt="logo" src={baseTeam.logo} className="image"></img>
          </div>
        ))}
      </div>
      <div className="player-container">
        {selectedTeam ? (
          selectedTeam.players.map((player, idx) => (
            <div className="player" key={idx}>
              {" "}
              <PlayerCard player={player} logo={selectedTeam.logo}></PlayerCard>
            </div>
          ))
        ) : (
          <></>
        )}
      </div>
    </div>
  );
}
export default Auth(BookPage, null);

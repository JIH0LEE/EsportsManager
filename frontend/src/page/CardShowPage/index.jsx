import React, { useState, useEffect } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import PlayerCard from "../../component/PlayerCard";
import Auth from "../../hoc/Auth";
import "./style.css";

function CardShowPage() {
  const [players, setPlayers] = useState([]);

  useEffect(() => {
    axios.get(API_SERVER + "/api/player").then((res) => {
      setPlayers(res.data);
    });
  }, []);

  return (
    <div className="card-show-page-container">
      {players.map((player, idx) => (
        <PlayerCard key={idx} player={player}></PlayerCard>
      ))}
    </div>
  );
}
export default Auth(CardShowPage, null);

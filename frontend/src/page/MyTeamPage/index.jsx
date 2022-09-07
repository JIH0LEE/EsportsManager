import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import "./style.css";
import Auth from "../../hoc/Auth";
import axios from "axios";
import { API_SERVER } from "../../config";
import PlayerCard from "../../component/PlayerCard";
function MyTeamPage() {
  const [teamName, setTeamName] = useState("");
  const [headCoachName, setHeadCoachName] = useState("");
  const [top, setTop] = useState(null);
  const [jng, setJng] = useState(null);
  const [mid, setMid] = useState(null);
  const [adc, setAdc] = useState(null);
  const [sup, setSup] = useState(null);
  const [sub, setSub] = useState([]);
  const [baseTeam, setBaseTeam] = useState(null);
  const [logo, setLogo] = useState(null);

  const { userData } = useSelector((state) => state);

  useEffect(() => {
    axios.get(API_SERVER + `/api/my-team/${userData.id}`).then((res) => {
      setTeamName(res.data.name);
      setHeadCoachName(res.data.headCoach.name);
      setTop(res.data.top);
      setJng(res.data.jng);
      setMid(res.data.mid);
      setAdc(res.data.adc);
      setSup(res.data.sup);
      setSub(res.data.sub);
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="myteam-page-container">
      <div className="team-name">{teamName}</div>
      <div className="cards-container">
        {top ? (
          <PlayerCard player={top.player} logo={logo}></PlayerCard>
        ) : (
          <></>
        )}
        {jng ? (
          <PlayerCard player={jng.player} logo={logo}></PlayerCard>
        ) : (
          <></>
        )}
        {mid ? (
          <PlayerCard player={mid.player} logo={logo}></PlayerCard>
        ) : (
          <></>
        )}
        {adc ? (
          <PlayerCard player={adc.player} logo={logo}></PlayerCard>
        ) : (
          <></>
        )}
        {sup ? (
          <PlayerCard player={sup.player} logo={logo}></PlayerCard>
        ) : (
          <></>
        )}
        {sub.map((subPlayer, idx) => (
          <PlayerCard
            key={idx}
            player={subPlayer.player}
            logo={logo}
          ></PlayerCard>
        ))}
      </div>
    </div>
  );
}
export default Auth(MyTeamPage, true);

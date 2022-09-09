import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import "./style.css";
import Auth from "../../hoc/Auth";
import axios from "axios";
import { API_SERVER } from "../../config";
import Player from "./component/Player";
import Sponsor from "./component/Sponsor";
import Enterprise from "./component/Enterprise";
function MyTeamPage() {
  const [teamId, setTeamId] = useState(0);
  const [teamName, setTeamName] = useState("");
  const [top, setTop] = useState(null);
  const [jng, setJng] = useState(null);
  const [mid, setMid] = useState(null);
  const [adc, setAdc] = useState(null);
  const [sup, setSup] = useState(null);
  const [sub, setSub] = useState([]);
  const [logo, setLogo] = useState(null);
  const [selected, setSelected] = useState("PLAYER");

  const { userData } = useSelector((state) => state);

  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(API_SERVER + `/api/league/league-info/${userData.id}`)
      .then((res) => {
        if (!res.data.success) {
          navigate("/make-team");
        }
      });

    axios.get(API_SERVER + `/api/my-team/${userData.id}`).then((res) => {
      setTeamId(res.data.id);
      setTeamName(res.data.name);
      setLogo(res.data.baseTeam.logo);
      setTop(res.data.top);
      setJng(res.data.jng);
      setMid(res.data.mid);
      setAdc(res.data.adc);
      setSup(res.data.sup);
      setSub(res.data.sub);
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  const selectedComponent = () => {
    if (selected === "PLAYER") {
      return (
        <Player
          id={teamId}
          top={top}
          jng={jng}
          mid={mid}
          adc={adc}
          sup={sup}
          sub={sub}
          logo={logo}
        ></Player>
      );
    } else if (selected === "SPONSOR") {
      return <Sponsor></Sponsor>;
    } else {
      return <Enterprise></Enterprise>;
    }
  };
  return (
    <div className="myteam-page-container background basic">
      <div className="team-info-container basic">
        <div className="logo-container">
          <img alt="logo" className="image" src={logo}></img>
        </div>
        <div className="team-name">{teamName}</div>
      </div>
      <div className="menu-container">
        <div
          className={`menu button${selected === "PLAYER" ? " clicked" : ""}`}
          onClick={() => {
            setSelected("PLAYER");
          }}
        >
          선수 관리
        </div>
        <div
          className={`menu button${selected === "SPONSOR" ? " clicked" : ""}`}
          onClick={() => {
            setSelected("SPONSOR");
          }}
        >
          스폰서 관리
        </div>
        <div
          className={`menu button${
            selected === "ENTERPRISE" ? " clicked" : ""
          }`}
          onClick={() => {
            setSelected("ENTERPRISE");
          }}
        >
          사업 관리
        </div>
      </div>
      {selectedComponent()}
    </div>
  );
}
export default Auth(MyTeamPage, true);

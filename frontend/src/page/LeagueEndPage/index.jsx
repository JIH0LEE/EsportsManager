import React, { useState, useEffect } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import { useSelector } from "react-redux/es/exports";
import { useNavigate } from "react-router-dom";
import Auth from "../../hoc/Auth";
import "./style.css";

function LeagueEndPage() {
  const { userData } = useSelector((state) => state);
  const [leagueRank, setLeagueRank] = useState([]);
  const navigate = useNavigate();
  
  useEffect(() => {
    axios
      .get(API_SERVER + `/api/league/league-rank/${userData.id}`)
      .then((res) => {
        setLeagueRank(res.data);
        console.log(res.data);
      })
      .catch((err) => {
        alert(err);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const submit = () => {
    axios
      .post(API_SERVER + `/api/league/${userData.id}`)
      .then((res) => {
        alert(res.data.message);
        navigate("/");
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <div className="league-end-container background">
      <div className="label-container label">시즌이 종료되었습니다!</div>
      <button className="button" onClick={submit}>
        새로운 시즌 시작
      </button>
      <div className="rank-container basic">
        <div className="label-container label">순위표</div>
        {leagueRank.map((team, idx) => (
          <div className="rank-body ">
            <span style={{ color: "rgb(224, 171, 28)" }}>{idx + 1}.</span>
            <div className="team-name">{team.teamName}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
export default Auth(LeagueEndPage, true);

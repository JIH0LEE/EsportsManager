import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Auth from "../../hoc/Auth";
import axios from "axios";
import "./style.css";
import PlayerCard from "../../component/PlayerCard";
import { API_SERVER } from "../../config";

function BookPage() {
  const { userData } = useSelector((state) => state);
  const [teamName, setTeamName] = useState("");
  const [baseTeams, setBaseTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(null);
  const navigate = useNavigate();
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
  const trim = (elem) => {
    return elem.trim();
  };
  const teamNameHandler = (e) => {
    setTeamName(e.target.value);
  };

  const submit = () => {
    if (trim(teamName) === "") {
      alert("팀 이름을 입력하세요");
      return;
    }
    const body = {
      name: trim(teamName),
      headCoachId: userData.id,
      baseTeamId: selectedTeam.id,
    };
    console.log(body);
    axios
      .post(API_SERVER + "/api/league", body)
      .then((res) => {
        navigate("/my-team");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="make-team-container background basic">
      <div className="label-container label">팀 생성</div>
      <div className="sub-label">팀 이름</div>
      <input
        type="text"
        className="team-name-container"
        placeholder="팀 이름을 입력하세요"
        value={teamName}
        onChange={teamNameHandler}
      ></input>
      <div className="sub-label">팀 선택하기</div>
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
      <div className="submit-container">
        <div className="create-button button2" onClick={submit}>
          {" "}
          팀 생성
        </div>
      </div>
    </div>
  );
}
export default Auth(BookPage, null);

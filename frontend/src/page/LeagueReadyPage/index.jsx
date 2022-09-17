import React, { useState, useEffect } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import { useSelector } from "react-redux/es/exports";
import { useNavigate } from "react-router-dom";
import Auth from "../../hoc/Auth";
import "./style.css";

function GameReadyPage() {
  const { userData } = useSelector((state) => state);
  const [isMyGame, setMyGame] = useState(false);
  const [isGame, setGame] = useState(false);
  const [day, setDay] = useState(0);
  const [teams, setTeams] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!userData.isLogin) {
      alert("로그인이 필요합니다");
      navigate("/login");
    }
    axios
      .get(API_SERVER + `/api/league/league-info/${userData.id}`)
      .then((res) => {
        if (!res.data.success) {
          alert("팀을 생성해주세요");
          navigate("/make-team");
          return;
        }
        if (res.data.day > 61) {
          navigate("/league-end");
          return;
        }
        setMyGame(res.data.myGame);
        setGame(res.data.game);
        setTeams(res.data.teams);
        setDay(res.data.day);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const toPersonalScehdule = () => {
    navigate("/personal-schedule", {
      state: {
        game: isGame,
        teams: teams,
        day: day,
      },
    });
  };
  const toGameReady = () => {
    navigate("/game-ready", {
      state: {
        game: isGame,
        teams: teams,
        day: day,
      },
    });
  };

  return (
    <div className="league-ready-container background">
      <div className="label-container label">리그 정보</div>
      <div className="league-info-container">
        <div className="day-container basic">Day {day}</div>
        <div className="match-container basic">
          {isGame ? (
            <>
              {teams.length === 4 ? (
                <>
                  <div className="match">
                    MATCH 1 : {teams[0]} vs {teams[1]}
                  </div>
                  <br></br>
                  <div className="match">
                    MATCH 2 : {teams[2]} vs {teams[3]}
                  </div>
                </>
              ) : (
                <></>
              )}
            </>
          ) : (
            <>경기가 없습니다.</>
          )}
        </div>
      </div>
      <button
        className="button"
        onClick={toPersonalScehdule}
        disabled={isMyGame}
      >
        개인 스케줄 관리
      </button>
      <button className="button" onClick={toGameReady} disabled={!isMyGame}>
        경기 진행
      </button>
    </div>
  );
}
export default Auth(GameReadyPage, true);

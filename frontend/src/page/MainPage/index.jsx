import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Auth from "../../hoc/Auth";
import axios from "axios";
import "./style.css";
import { API_SERVER } from "../../config";

function MainPage() {
  const { userData } = useSelector((state) => state);
  const [isMyTeam, setIsMyTeam] = useState(false);
  const [leagueRank, setLeagueRank] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    if (userData.isLogin) {
      axios
        .get(API_SERVER + `/api/league/league-info/${userData.id}`)
        .then((res) => {
          setIsMyTeam(res.data.success);
          if (res.data.success) {
            axios
              .get(API_SERVER + `/api/league/league-rank/${userData.id}`)
              .then((res) => {
                setLeagueRank(res.data);
              })
              .catch((err) => {
                alert(err);
              });
          }
        })
        .catch((err) => {
          alert(err);
        });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="main-container">
      <div className="main-1 background2"></div>
      <div className="main-2">
        <div className="info-container background2">
          <div className="label-container label">Information</div>
          <div className="content-container basic">
            Esports Manager는 LoL 기반 2차 컨텐츠 게임으로 LoL과 함께 높은
            관심을 보이는 e스포츠 대회를 접목하여 LoL을 즐기는 유저들에게 더
            다양한 수요를 충족해주고 색다른 재미를 선보일 수 있습니다. <br />
            <br />
            LCK 리그 선수들로 구성된 자신의 팀을 만들고 챔피언을 선택하여 게임을
            진행할 수 있으며 선수들의 능력치와 챔피언의 능력치를 기반으로
            시뮬레이션을 진행합니다.
            <br />
            <br />
            선수들을 직접 육성하고 선수들을 영입하고 자신만의 팀을 이끌어
            가세요!
          </div>
        </div>
        <div className="ranking-container background2 ">
          <div className="label-container label">순위</div>
          <div className="content-container basic">
            {userData.isLogin ? (
              <>
                {isMyTeam ? (
                  <>
                    <div className="table-head basic-1">
                      <div className="team-name">팀</div>
                      <div className="win">승</div>
                      <div className="lose">패</div>
                      <div className="win-point">승점</div>
                    </div>
                    {leagueRank.map((team, idx) => (
                      <div className="table-body ">
                        <div className="team-name">{team.teamName}</div>
                        <div className="win">{team.matchWin}</div>
                        <div className="lose">{team.matchLose}</div>
                        <div className="win-point">{team.winPoint}</div>
                      </div>
                    ))}
                  </>
                ) : (
                  <>
                    <div
                      className="direct-button button2"
                      onClick={() => {
                        navigate("/make-team");
                      }}
                    >
                      팀을 생성해주세요
                    </div>
                  </>
                )}
              </>
            ) : (
              <div
                className="direct-button button2"
                onClick={() => {
                  navigate("/login");
                }}
              >
                로그인이 필요합니다
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
export default Auth(MainPage, null);

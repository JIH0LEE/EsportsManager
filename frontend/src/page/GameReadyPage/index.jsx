import React, { useState, useEffect } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import { useNavigate, useLocation } from "react-router-dom";

import { useSelector } from "react-redux";
import Player from "./component/Player";
import Auth from "../../hoc/Auth";
import "./style.css";

function GameReadyPage() {
  const [ids, setIds] = useState([]);
  const [swap, setSwap] = useState(null);
  const [teamId, setTeamId] = useState(0);
  const positionArr = ["TOP", "JNG", "MID", "ADC", "SUB"];

  const { userData } = useSelector((state) => state);

  const location = useLocation();
  const isGame = location.state.game;
  const teams = location.state.teams;
  const day = location.state.day;
  useEffect(() => {
    axios.get(API_SERVER + `/api/my-team/${userData.id}`).then((res) => {
      let arr = [];
      arr.push(res.data.top);
      arr.push(res.data.jng);
      arr.push(res.data.mid);
      arr.push(res.data.adc);
      arr.push(res.data.sup);
      let arr2 = arr.concat(res.data.sub);
      setIds(arr2);
      setTeamId(res.data.id);
    });
  }, []);
  const change = (idx) => {
    const arr = [...ids];
    let temp = arr[swap];
    arr[swap] = arr[idx];
    arr[idx] = temp;
    setIds(arr);
    setSwap(null);
    changeEntry(arr);
  };
  const isSub = () => {
    if (ids.length < 6) {
      return false;
    } else {
      return true;
    }
  };
  const changeEntry = (arr) => {
    let myPlayerList = [];
    arr.forEach((element) => {
      myPlayerList.push(element.id);
    });
    let body = {
      id: teamId,
      myPlayerList: myPlayerList,
    };
    axios
      .post(API_SERVER + "/api/my-team/change-entry", body)
      .then(() => {})
      .catch((err) => {
        alert(err);
      });
  };
  const submit = () => {
    axios
      .post(API_SERVER + `/api/league/league-process/${userData.id}`)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div className="entry-change-container">
      <div className="label-container label">리그 정보</div>
      <div className="league-info-container basic">
        <div className="day-container">Day {day}</div>
        <div className="match-container">
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
      <div className="label">엔트리 변경</div>
      <div className="small-label basic">REGULAR</div>
      <div className="regular-container">
        {ids.slice(0, 5).map((e, idx) => {
          return (
            <div key={idx} className="all-container">
              <div className="position-container basic">{positionArr[idx]}</div>
              <div
                className="card-back button"
                onClick={() => {
                  if (swap === null) {
                    setSwap(idx);
                  } else {
                    change(idx);
                  }
                }}
                key={idx}
              >
                <Player player={e}></Player>
                {swap !== null ? (
                  <>
                    {swap === idx ? (
                      <>
                        <div className="swap">Selected</div>
                      </>
                    ) : (
                      <>
                        <div className="non-swap">Swap</div>
                      </>
                    )}
                  </>
                ) : (
                  <></>
                )}
              </div>
            </div>
          );
        })}
      </div>
      <div className="small-label basic">SUB</div>
      {isSub() ? (
        <div className="regular-container">
          {ids.slice(5).map((e, idx) => {
            return (
              <div className="all-container">
                <div
                  className="card-back button"
                  onClick={() => {
                    if (swap === null) {
                      setSwap(idx + 5);
                    } else {
                      change(idx + 5);
                    }
                  }}
                  key={idx}
                >
                  <Player player={e}></Player>
                  {swap ? (
                    <>
                      {swap === idx + 5 ? (
                        <>
                          <div className="swap">Selected</div>
                        </>
                      ) : (
                        <>
                          <div className="non-swap">Swasp</div>
                        </>
                      )}
                    </>
                  ) : (
                    <></>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      ) : (
        <div className="no-sub basic">서브 선수가 없습니다</div>
      )}
      <div className="button-container">
        <div className="submit button" onClick={submit}>
          진행하기
        </div>
      </div>
    </div>
  );
}
export default Auth(GameReadyPage, true);

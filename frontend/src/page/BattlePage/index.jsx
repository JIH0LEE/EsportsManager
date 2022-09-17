import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Tower from "./component/Tower";
import Board from "./component/Board";
import ChampionBar from "./component/ChampionBar";
import { API_SERVER } from "../../config";
import Auth from "../../hoc/Auth";

import "./style.css";
import axios from "axios";

function BattlePage() {
  const [setInfo, setSetInfo] = useState(null);
  const [logs, setLog] = useState();
  const [isFinished, setIsFinished] = useState(false);
  const [isSimulation, setIsSimulation] = useState(false);
  const location = useLocation();
  const data = location.state;
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(API_SERVER + `/api/set/${data.id}`).then((res) => {
      setSetInfo(res.data);

      setIsFinished(res.data.finished);
      if (res.data.gameLog !== "") {
        setLog(getLogInfo(res.data.gameLog));
      }
    });
  }, []);

  const submit = () => {
    setIsSimulation(true);
    axios.post(API_SERVER + `/api/set/play/${data.id}`).then((res) => {
      setSetInfo(res.data);
      setLog(getLogInfo(res.data.gameLog));
      setIsFinished(res.data.finished);
      setIsSimulation(false);
    });
  };
  const getScore = (setCount, gameScore) => {
    if (setCount === 0) {
      return "0:0";
    } else if (setCount === 1) {
      if (gameScore === 1) {
        return "1:0";
      } else {
        return "0:1";
      }
    } else if (setCount === 2) {
      if (gameScore === 2) {
        return "2:0";
      } else if (gameScore === 0) {
        return "1:1";
      } else {
        return "0:2";
      }
    } else {
      if (gameScore === 1) {
        return "2:1";
      } else {
        return "1:2";
      }
    }
  };
  const getLogInfo = (arr) => {
    const changeTimeFormat = (time) => {
      let minute = parseInt(time.split("분")[0]);
      let second = parseInt(time.split("분")[1].split("초")[0]);
      return 60 * minute + second;
    };
    const compareFunction = (a, b) => {
      if (changeTimeFormat(a.time) > changeTimeFormat(b.time)) {
        return 1;
      } else if (changeTimeFormat(a.time) === changeTimeFormat(b.time)) {
        return 0;
      } else {
        return -1;
      }
    };
    let temp = arr.split(";");
    var result = [];
    for (let i = 0; i < temp.length - 1; i++) {
      let tmp = temp[i].split("/");
      result.push({
        time: tmp[0],
        desc: tmp[1],
      });
    }
    result = result.sort(compareFunction);
    result.push({ time: "경기 종료", desc: temp[temp.length - 1] });
    return result;
  };

  return (
    <>
      {setInfo ? (
        <>
          <div className="score-board">
            <div className="blue-board">
              {setInfo.blue
                ? setInfo.gameMatch.myTeam.name
                : setInfo.gameMatch.oppositeTeam.name}
            </div>
            <div className="score">
              {getScore(
                setInfo.gameMatch.gameSetCount,
                setInfo.gameMatch.gameScore
              )}
            </div>
            <div className="red-board">
              {setInfo.blue
                ? setInfo.gameMatch.oppositeTeam.name
                : setInfo.gameMatch.myTeam.name}
            </div>
          </div>
        </>
      ) : (
        <></>
      )}

      <div className="battle-container">
        {isFinished ? (
          <button
            className="button"
            onClick={() => {
              navigate("/league-ready");
            }}
          >
            경기 완료
          </button>
        ) : (
          <>
            {" "}
            {isSimulation ? (
              <div className="basic ongoing">경기 진행중입니다 ...</div>
            ) : (
              <button className="button" onClick={submit}>
                시뮬레이션 진행
              </button>
            )}
          </>
        )}

        {setInfo ? (
          <>
            <image
              src="./image/logo.png"
              style={{ width: "50px", height: "50px" }}
            ></image>
            {setInfo.towerList.slice(0, 6).map((tower, idx) => (
              <Tower
                key={idx}
                id={idx + 1}
                tower={tower}
                color={"blue"}
              ></Tower>
            ))}
            {setInfo.towerList.slice(6, 12).map((tower, idx) => (
              <Tower key={idx} id={idx + 7} tower={tower} color={"red"}></Tower>
            ))}
            <Board data={setInfo}></Board>
            <ChampionBar data={setInfo}></ChampionBar>
            {setInfo.gameLog !== "" ? (
              <div className="select basic">
                <div className="title">전투 기록</div>
                {logs.map((log) => (
                  <div className="log">
                    <div className="time">{log.time}</div>
                    <div className="description">{log.desc}</div>
                  </div>
                ))}
              </div>
            ) : (
              <></>
            )}
          </>
        ) : (
          <></>
        )}
      </div>
    </>
  );
}
export default Auth(BattlePage, true);

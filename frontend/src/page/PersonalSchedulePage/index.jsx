import React, { useState, useEffect } from "react";
import { API_SERVER } from "../../common";
import { useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import axios from "axios";
import "./style.css";
import Auth from "../../hoc/Auth";
import Player from "./component/Player";

function PersonalSchedulePage() {
  const [top, setTop] = useState(null);
  const [jng, setJng] = useState(null);
  const [mid, setMid] = useState(null);
  const [adc, setAdc] = useState(null);
  const [sup, setSup] = useState(null);
  const [sub, setSub] = useState([]);
  const [submitData, setSubmitData] = useState([]);
  const { userData } = useSelector((state) => state);
  const location = useLocation();
  const isGame = location.state.game;
  const teams = location.state.teams;
  const day = location.state.day;

  const changeSubmitData = (idx, id) => {
    const changedArray = [...submitData];
    changedArray[idx].scheduleId = id;
    setSubmitData(changedArray);
  };

  const submit = () => {
    const body = { headCoachId: userData.id, submitData: submitData };
    axios
      .post(API_SERVER + "/api/my-team/schedule", body)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    axios.get(API_SERVER + `/api/my-team/${userData.id}`).then((res) => {
      const obj = [];

      setTop(res.data.top);
      setJng(res.data.jng);
      setMid(res.data.mid);
      setAdc(res.data.adc);
      setSup(res.data.sup);
      setSub(res.data.sub);
      obj.push({ id: res.data.top.id, scheduleId: 1 });
      obj.push({ id: res.data.jng.id, scheduleId: 1 });
      obj.push({ id: res.data.mid.id, scheduleId: 1 });
      obj.push({ id: res.data.adc.id, scheduleId: 1 });
      obj.push({ id: res.data.sup.id, scheduleId: 1 });
      for (let subPlayer of res.data.sub) {
        obj.push({ id: subPlayer.id, scheduleId: 1 });
      }
      setSubmitData(obj);
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return (
    <div className="schedule-page background basic">
      <div className="label-container label">리그 정보</div>
      <div className="league-info-container">
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
      <div className="label-container label">개인 스케줄 관리</div>
      <div className="schedule-info">
        운동 : 컨디션이 약간 상승하고 경험치가 약간 상승합니다.
        <br></br>
        명상 : 컨디션이 최상이 됩니다.
        <br></br>
        방송 : 컨디션이 소폭 하락하지만 추가적인 자본을 얻습니다.
        <br></br>
        연습 : 컨디션이 최악이 되지만 경험치가 대량 상승합니다.
      </div>
      <div className="personal-schedule-container">
        <Player
          top={top}
          jng={jng}
          mid={mid}
          adc={adc}
          sup={sup}
          sub={sub}
          submitData={submitData}
          func={changeSubmitData}
        ></Player>
      </div>
      <div className="button-container">
        <div className="submit button" onClick={submit}>
          진행하기
        </div>
      </div>
    </div>
  );
}
export default Auth(PersonalSchedulePage, true);

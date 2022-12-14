import React, { useState, useEffect } from "react";
import { API_SERVER } from "../../common";
import { useSelector } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
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
  const navigate = useNavigate();

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

  const changeSubmitData = (idx, id) => {
    const changedArray = [...submitData];
    changedArray[idx].scheduleId = id;
    setSubmitData(changedArray);
  };
  const toLeagueReady = () => {
    navigate("/league-ready");
  };
  const submit = () => {
    const body = {
      headCoachId: userData.id,
      game: isGame,
      submitData: submitData,
    };
    axios
      .post(API_SERVER + "/api/league/league-process", body)
      .then((res) => {
        alert(res.data.message);
        toLeagueReady();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="schedule-page background basic">
      <div className="label-container label">?????? ??????</div>
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
            <>????????? ????????????.</>
          )}
        </div>
      </div>
      <div className="label-container label">?????? ????????? ??????</div>
      <div className="schedule-info">
        ?????? : ???????????? ?????? ???????????? ???????????? ?????? ???????????????.
        <br></br>
        ?????? : ???????????? ????????? ?????????.
        <br></br>
        ?????? : ???????????? ?????? ??????????????? ???????????? ????????? ????????????.
        <br></br>
        ?????? : ???????????? ????????? ????????? ???????????? ?????? ???????????????.
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
          ????????????
        </div>
      </div>
    </div>
  );
}
export default Auth(PersonalSchedulePage, true);

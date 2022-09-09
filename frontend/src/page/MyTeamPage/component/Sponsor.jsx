import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { API_SERVER } from "../../../common";

import "../style.css";

const Sponsor = ({ id }) => {
  const [already, setAlready] = useState([]);
  const [disable, setDisable] = useState([]);
  const [enable, setEnable] = useState([]);
  useEffect(() => {
    axios.get(API_SERVER + `/api/my-team/sponsor/${id}`).then((res) => {
      setAlready(res.data.alreadySponsor);
      setDisable(res.data.disableSponsor);
      setEnable(res.data.enableSponsor);
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return (
    <div className="sponsor-container">
      <div className="label-container">계약된 스폰서</div>
      {already.length === 0 ? (
        <div className="none-container">계약된 스폰서가 없습니다</div>
      ) : (
        <>
          {already.map((sponsor, idx) => (
            <div className="enter-container" key={idx}>
              <div className="left">
                <img alt="logo" src={sponsor.image} className="image"></img>
              </div>
              <div className="right">
                <div className="title">{sponsor.name}</div>
                <div className="description">
                  승리 시 {sponsor.money}원 만큼 얻습니다.
                  <br></br>
                  <br></br> 계약 조건: {sponsor.win} 이상
                </div>
                <div className="button-container"></div>
              </div>
            </div>
          ))}
        </>
      )}
      <div className="label-container">계약 가능한 스폰서</div>
      {enable.length === 0 ? (
        <div className="none-container">계약된 스폰서가 없습니다</div>
      ) : (
        <>
          {enable.map((sponsor, idx) => (
            <div className="enter-container" key={idx}>
              <div className="left">
                <img alt="logo" src={sponsor.image} className="image"></img>
              </div>
              <div className="right">
                <div className="title">{sponsor.name}</div>
                <div className="description">
                  승리 시 {sponsor.money}원 만큼 얻습니다.
                  <br></br>
                  <br></br> 계약 조건: {sponsor.win} 이상
                </div>
                <div className="button-container">
                  <div className="register-button button2 background">
                    등록하기
                  </div>
                </div>
              </div>
            </div>
          ))}
        </>
      )}
      <div className="label-container">계약 불가능한 스폰</div>
      {disable.length === 0 ? (
        <div className="none-container">계약된 스폰서가 없습니다</div>
      ) : (
        <>
          {disable.map((sponsor, idx) => (
            <div className="enter-container" key={idx}>
              <div className="left">
                <img alt="logo" src={sponsor.image} className="image"></img>
              </div>
              <div className="right">
                <div className="title">{sponsor.name}</div>
                <div className="description">
                  승리 시 {sponsor.money}원 만큼 얻습니다.
                  <br></br>
                  <br></br> 계약 조건: {sponsor.win} 이상
                </div>
                <div className="button-container"></div>
              </div>
            </div>
          ))}
        </>
      )}
    </div>
  );
};
export default Sponsor;

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { API_SERVER } from "../../../common";
import "../style.css";

const Enterprise = ({ id }) => {
  const [yet, setYet] = useState([]);
  const [ing, setIng] = useState([]);
  useEffect(() => {
    axios.get(API_SERVER + `/api/my-team/enterprise/${id}`).then((res) => {
      setIng(res.data.ing);
      setYet(res.data.yet);
    });

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="sponsor-container">
      <div className="label-container">진행중인 사업</div>
      {ing.length === 0 ? (
        <div className="none-container">진행중인 사업이 없습니다</div>
      ) : (
        <>
          {ing.map((enterprise, idx) => (
            <div className="enter-container" key={idx}>
              <div className="left">
                <img alt="logo" src={enterprise.image} className="image"></img>
              </div>
              <div className="right">
                <div className="title">{enterprise.name}</div>
                <div className="description">
                  {enterprise.cost}원을 소모하는 대신 매일{" "}
                  {enterprise.earningMoney}원 만큼 얻습니다.
                </div>
                <div className="button-container"></div>
              </div>
            </div>
          ))}
        </>
      )}

      <div className="label-container">미등록 사업</div>
      {yet.length === 0 ? (
        <div className="none-container">미등록된 사업이 없습니다</div>
      ) : (
        <>
          {yet.map((enterprise, idx) => (
            <div className="enter-container" key={idx}>
              <div className="left">
                <img alt="logo" src={enterprise.image} className="image"></img>
              </div>
              <div className="right">
                <div className="title">{enterprise.name}</div>
                <div className="description">
                  {enterprise.cost}원을 소모하는 대신 매일{" "}
                  {enterprise.earningMoney}원 만큼 얻습니다.
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
    </div>
  );
};
export default Enterprise;

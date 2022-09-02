import React, { useState, useEffect } from "react";
import "./style.css";
import Auth from "../../hoc/Auth";
import axios from "axios";
import { API_SERVER } from "../../config";
function BanpickPage() {
  const [position, setPosition] = useState("ALL");
  const [champions, setChampions] = useState([]);
  const [showChapions, setShowChapions] = useState([]);
  useEffect(() => {
    axios
      .get(API_SERVER + "/api/champion")
      .then((res) => {
        setChampions(res.data);
        setShowChapions(res.data);
      })
      .catch((err) => {
        alert(err);
      });
  }, []);
  useEffect(() => {
    let result = null;
    if (position === "ALL") {
      result = champions;
    } else {
      result = champions.filter((champion) => champion.position === position);
    }
    setShowChapions(result);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [position]);
  return (
    <div className="banpick-container">
      <div className="command-container">
        <div className="command">Ban</div>
        <div className="submit-button button">확인</div>
      </div>
      <div className="score-board">
        <div className="blue-board">{"내 팀"}</div>
        <div className="score">
          {1}:{0}
        </div>
        <div className="red-board">{"너 팀"}</div>
      </div>
      <div className="banpick-board">
        <div className="pick-list">
          <div className="blue-list"></div>
          <div className="champion-list">
            <div className="button-container">
              <button
                className={`position-button ${
                  position === "ALL" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("ALL");
                }}
              >
                전체
              </button>
              <button
                className={`position-button ${
                  position === "TOP" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("TOP");
                }}
              >
                탑
              </button>
              <button
                className={`position-button ${
                  position === "JUNGLE" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("JUNGLE");
                }}
              >
                정글
              </button>
              <button
                className={`position-button ${
                  position === "MIDDLE" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("MIDDLE");
                }}
              >
                미드
              </button>
              <button
                className={`position-button ${
                  position === "ADC" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("ADC");
                }}
              >
                원딜
              </button>
              <button
                className={`position-button ${
                  position === "SUPPORT" ? "active" : "no-active"
                }`}
                onClick={() => {
                  setPosition("SUPPORT");
                }}
              >
                서폿
              </button>
            </div>
            <div className="champion-icon-container">
              {showChapions.map((champion, idx) => (
                <div key={idx} className="champion-item">
                  <img
                    alt={champion.koreanName}
                    src={champion.image}
                    className="img"
                  ></img>
                </div>
              ))}
            </div>
          </div>
          <div className="red-list"></div>
        </div>
        <div className="ban-list"></div>
      </div>
    </div>
  );
}
export default Auth(BanpickPage, true);

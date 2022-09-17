import React, { useState } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import { useNavigate, useLocation } from "react-router-dom";
import PlayerCard from "../../component/PlayerCard";
import Auth from "../../hoc/Auth";
import "./style.css";

function EntryChangePage() {
  const location = useLocation();
  const [ids, setIds] = useState(location.state.player);
  const logo = location.state.logo;
  const [swap, setSwap] = useState(null);
  const positionArr = ["TOP", "JNG", "MID", "ADC", "SUB"];
  const navigate = useNavigate();

  const change = (idx) => {
    const arr = [...ids];
    let temp = arr[swap];
    arr[swap] = arr[idx];
    arr[idx] = temp;
    setIds(arr);
    setSwap(null);
  };
  const isSub = () => {
    if (ids.length < 6) {
      return false;
    } else {
      return true;
    }
  };
  const submit = () => {
    let myPlayerList = [];
    ids.forEach((element) => {
      myPlayerList.push(element.id);
    });
    let body = {
      id: location.state.id,
      myPlayerList: myPlayerList,
    };
    axios
      .post(API_SERVER + "/api/my-team/change-entry", body)
      .then((res) => {
        alert("변경되었습니다.");
        navigate("/my-team");
      })
      .catch((err) => {
        alert(err);
      });
  };
  return (
    <div className="entry-change-container">
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
                <PlayerCard player={e.player} logo={logo}></PlayerCard>
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
                  <PlayerCard player={e.player} logo={logo}></PlayerCard>
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
          저장하기
        </div>
      </div>
    </div>
  );
}
export default Auth(EntryChangePage, true);

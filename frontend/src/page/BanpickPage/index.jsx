import React, { useState, useEffect } from "react";
import "./style.css";
import Auth from "../../hoc/Auth";
import axios from "axios";
import { useSelector } from "react-redux";
import { API_SERVER } from "../../config";
import BluePick from "./component/BluePick";
import RedPick from "./component/RedPick";
import Ban from "./component/Ban";
import { click } from "@testing-library/user-event/dist/click";
function BanpickPage() {
  const [position, setPosition] = useState("ALL");
  const [champions, setChampions] = useState([]);
  const [showChapions, setShowChapions] = useState([]);
  const [blueTeam, setBlueTeam] = useState(null);
  const [bluePick, setBluePick] = useState([null, null, null, null, null]);
  const [redPick, setRedPick] = useState([null, null, null, null, null]);
  const [blueBan, setBlueBan] = useState([null, null, null, null, null]);
  const [redBan, setRedBan] = useState([null, null, null, null, null]);
  const [bluePickId, setBluePickId] = useState([]);
  const [redPickId, setRedPickId] = useState([]);
  const [blueBanId, setBlueBanId] = useState([]);
  const [redBanId, setRedBanId] = useState([]);
  const [redTeam, setRedTeam] = useState(null);
  const [isBlue, setIsBlue] = useState(false);
  const [clickedChamp, setClickedChamp] = useState(0);
  const [stageNum, setStageNum] = useState(0);
  const [aiPickedPos, setAiPickedPos] = useState([]);
  const [aiUnPickedPos, setAiUnPickedPos] = useState([
    "TOP",
    "JUNGLE",
    "MIDDLE",
    "ADC",
    "SUPPORT",
  ]);
  const { userData } = useSelector((state) => state);
  const stage = [
    "Blue Ban 1",
    "Red Ban 1",
    "Blue Ban 2",
    "Red Ban 2",
    "Blue Ban 3",
    "Red Ban 3",
    "Blue Pick 1",
    "Red Pick 1",
    "Red Pick 2",
    "Blue Pick 2",
    "Blue Pick 3",
    "Red Pick 3",
    "Red Ban 4",
    "Blue Ban 4",
    "Red Ban 5",
    "Blue Ban 5",
    "Red Pick 4",
    "Blue Pick 4",
    "Blue Pick 5",
    "Red Pick 5",
    "Swap",
  ];

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
    axios
      .get(API_SERVER + `/api/match/${userData.id}`)
      .then((res) => {
        if (res.data.blue) {
          setBlueTeam(res.data.myTeam);
          setRedTeam(res.data.oppositeTeam);
          setIsBlue(res.data.blue);
        }
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
  const test = () => {
    console.log("_________________________");
    console.log(blueBan);
    console.log(redBan);
    console.log(bluePick);
    console.log(redPick);
  };
  test();
  const nextStage = () => {
    console.log(blueBanId.includes(clickedChamp.id));
    console.log(bluePickId.includes(clickedChamp.id));
    console.log(redBanId.includes(clickedChamp.id));
    console.log(redPickId.includes(clickedChamp.id));
    console.log(
      blueBanId.includes(clickedChamp.id) ||
        bluePickId.includes(clickedChamp.id) ||
        redBanId.includes(clickedChamp.id) ||
        redPickId.includes(clickedChamp.id)
    );
    const parsing = stage[stageNum].split(" ");
    if (parsing[0] === "Swap") {
      return;
    }

    if (isMyTurn()) {
      action();
    } else {
      aiAction();
    }
  };

  const isMyTurn = () => {
    const parsing = stage[stageNum].split(" ");
    const color = parsing[0];

    if ((isBlue && color === "Blue") || (!isBlue && color === "Red")) {
      return true;
    } else {
      return false;
    }
  };
  const clickable = (e) => {
    const champion = e.target.value;
    const parsing = stage[stageNum].split(" ");
    console.log(1);
    if (isBlue && parsing[0] === "Blue") {
      if (
        blueBanId.includes(champion.id) ||
        bluePickId.includes(champion.id) ||
        redBanId.includes(champion.id) ||
        redPickId.includes(champion.id)
      ) {
        return false;
      } else {
        return true;
      }
    }
    if (!isBlue && parsing[0] === "Red") {
      if (
        blueBanId.includes(champion.id) ||
        bluePickId.includes(champion.id) ||
        redBanId.includes(champion.id) ||
        redPickId.includes(champion.id)
      ) {
        return false;
      } else {
        return true;
      }
    }
    return false;
  };
  const aiSwap = () => {
    let swaped = [];
    const arr = ["TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"];
    var i = 0;
    for (i = 0; i < 5; i++) {
      swaped.push(redPick[aiPickedPos.indexOf(arr[i])]);
    }
    setRedPick(swaped);
    if (isBlue) {
      setRedPick(swaped);
    } else {
      setBluePick(swaped);
    }
  };
  const aiAction = () => {
    const parsing = stage[stageNum].split(" ");
    if (parsing[0] === "Swap") {
      return;
    }
    const cmd = parsing[1];
    const idx = parseInt(parsing[2]) - 1;
    if (cmd === "Pick") {
      const rand = Math.floor(Math.random() * aiUnPickedPos.length);
      const position = aiUnPickedPos[rand];
      const available = champions.filter(
        (champion) =>
          champion.position === position &&
          champion.tier >= 2 &&
          !blueBanId.includes(champion.id) &&
          !bluePickId.includes(champion.id) &&
          !redBanId.includes(champion.id) &&
          !redPickId.includes(champion.id)
      );
      const rand2 = Math.floor(Math.random() * available.length);
      const pickedChamp = available[rand2];

      const temp = [...aiUnPickedPos];
      temp.splice(rand, 1);
      setAiUnPickedPos(temp);

      const temp1 = [...aiPickedPos];
      temp1.push(position);
      setAiPickedPos(temp1);

      if (isBlue) {
        const temp2 = [...redPick];
        temp2[parseInt(idx)] = pickedChamp;
        setRedPick(temp2);

        const temp3 = [...redPickId];
        temp3.push(pickedChamp.id);
        setRedPickId(temp3);
      } else {
        const temp2 = [...bluePick];
        temp2[parseInt(idx)] = pickedChamp;
        setBluePick(temp2);

        const temp3 = [...bluePickId];
        temp3.push(pickedChamp.id);
        setBluePickId(temp3);
      }
    }
    if (cmd === "Ban") {
      const available = champions.filter(
        (champion) =>
          champion.tier >= 2 &&
          !blueBanId.includes(champion.id) &&
          !bluePickId.includes(champion.id) &&
          !redBanId.includes(champion.id) &&
          !redPickId.includes(champion.id)
      );
      const rand = Math.floor(Math.random() * available.length);
      const bannedChamp = available[rand];
      if (isBlue) {
        const temp2 = [...redBan];
        temp2[parseInt(idx)] = bannedChamp;
        setRedBan(temp2);

        const temp3 = [...redBanId];
        temp3.push(bannedChamp.id);
        setRedBanId(temp3);
      } else {
        const temp2 = [...blueBan];
        temp2[parseInt(idx)] = bannedChamp;
        setBlueBan(temp2);

        const temp3 = [...blueBanId];
        temp3.push(bannedChamp.id);
        setBlueBanId(temp3);
      }
    }
    setStageNum(stageNum + 1);
  };
  const action = () => {
    if (clickedChamp === 0) {
      alert("챔피언을 선택해주세요!");
      return;
    }
    const parsing = stage[stageNum].split(" ");
    if (parsing[0] === "Swap") {
      return;
    }
    const cmd = parsing[1];
    const idx = parseInt(parsing[2]) - 1;
    if (isBlue) {
      if (cmd === "Pick") {
        const temp = [...bluePick];
        temp[parseInt(idx)] = clickedChamp;
        setBluePick(temp);

        const temp3 = [...bluePickId];
        temp3.push(clickedChamp.id);
        setBluePickId(temp3);
      }
      if (cmd === "Ban") {
        const temp = [...blueBan];
        temp[parseInt(idx)] = clickedChamp;
        setBlueBan(temp);

        const temp3 = [...blueBanId];
        temp3.push(clickedChamp.id);
        setBlueBanId(temp3);
      }
    } else {
      if (cmd === "Pick") {
        const temp = [...redPick];
        temp[parseInt(idx) - 1] = clickedChamp;
        setRedPick(temp);

        const temp3 = [...redPickId];
        temp3.push(clickedChamp.id);
        setRedPickId(temp3);
      }
      if (cmd === "Ban") {
        const temp = [...redBan];
        temp[parseInt(idx) - 1] = clickedChamp;
        setRedBan(temp);

        const temp3 = [...redBanId];
        temp3.push(clickedChamp.id);
        setRedBanId(temp3);
      }
    }
    setStageNum(stageNum + 1);
    setClickedChamp(0);
  };

  const disableIcon = (champion) => {
    if (
      !blueBanId.includes(champion.id) &&
      !bluePickId.includes(champion.id) &&
      !redBanId.includes(champion.id) &&
      !redPickId.includes(champion.id)
    ) {
      return true;
    } else {
      return false;
    }
  };
  return (
    <div className="banpick-container">
      {blueTeam ? (
        <>
          <div className="command-container">
            <div className="command background basic">{stage[stageNum]}</div>
            {isMyTurn() ? (
              <div className="submit-button button" onClick={nextStage}>
                확인
              </div>
            ) : (
              <div className="submit-button button" onClick={nextStage}>
                상대 진행
              </div>
            )}
          </div>
          <div className="score-board">
            <div className="blue-board">{blueTeam.name}</div>
            <div className="score">
              {1}:{0}
            </div>
            <div className="red-board">{redTeam.name}</div>
          </div>
          <div className="banpick-board">
            <div className="pick-list">
              <BluePick bluePick={bluePick} blueTeam={blueTeam}></BluePick>
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
                    <button
                      key={idx}
                      className={`champion-item${
                        clickedChamp === champion ? " clicked-champ" : ""
                      }`}
                      onClick={() => {
                        setClickedChamp(champion);
                      }}
                      value={champion}
                      disabled={
                        !isMyTurn() ||
                        blueBanId.includes(champion.id) ||
                        bluePickId.includes(champion.id) ||
                        redBanId.includes(champion.id) ||
                        redPickId.includes(champion.id)
                      }
                    >
                      <img
                        alt={champion.koreanName}
                        src={champion.image}
                        className={`img ${
                          blueBanId.includes(champion.id) ||
                          bluePickId.includes(champion.id) ||
                          redBanId.includes(champion.id) ||
                          redPickId.includes(champion.id)
                            ? " gray-color"
                            : ""
                        }`}
                      ></img>
                    </button>
                  ))}
                </div>
              </div>
              <RedPick redTeam={redTeam} redPick={redPick}></RedPick>
            </div>
            <Ban redBan={redBan} blueBan={blueBan}></Ban>
          </div>
        </>
      ) : (
        <></>
      )}
      <button onClick={aiSwap}>test</button>
    </div>
  );
}
export default Auth(BanpickPage, true);

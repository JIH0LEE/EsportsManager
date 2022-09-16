import React, { useState } from "react";
import "../style.css";

const BluePick = ({ bluePick, blueTeam, isBlue, isSwapStage, func }) => {
  const [swap, setSwap] = useState(null);
  const change = (idx) => {
    const arr = [...bluePick];
    let temp = arr[swap];
    arr[swap] = arr[idx];
    arr[idx] = temp;

    setSwap(null);
    func(arr);
  };
  const isSwappable = () => {
    return isBlue && isSwapStage;
  };
  return (
    <div className="blue-list">
      <div className="pick-item">
        <div className="name-container"> {blueTeam.top.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (!isSwappable()) {
              return;
            }
            console.log(isSwapStage);
            if (swap === null) {
              setSwap(0);
            } else {
              change(0);
            }
          }}
        >
          <img
            src={bluePick[0] ? bluePick[0].image : bluePick[0]}
            className="image"
          ></img>
          {swap !== null && isSwappable() ? (
            <>
              {swap === 0 ? (
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
      <div className="pick-item">
        <div className="name-container"> {blueTeam.jng.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (!isSwappable()) {
              return;
            }
            if (swap === null) {
              setSwap(1);
            } else {
              change(1);
            }
          }}
        >
          <img
            src={bluePick[1] ? bluePick[1].image : bluePick[1]}
            className="image"
          ></img>
          {swap !== null && isSwappable() ? (
            <>
              {swap === 1 ? (
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
      <div className="pick-item">
        <div className="name-container"> {blueTeam.mid.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              if (!isSwappable()) {
                return;
              }
              setSwap(2);
            } else {
              change(2);
            }
          }}
        >
          <img
            src={bluePick[2] ? bluePick[2].image : bluePick[2]}
            className="image"
          ></img>
          {swap !== null && isSwappable() ? (
            <>
              {swap === 2 ? (
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
      <div className="pick-item">
        <div className="name-container"> {blueTeam.adc.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (!isSwappable()) {
              return;
            }
            if (swap === null) {
              setSwap(3);
            } else {
              change(3);
            }
          }}
        >
          <img
            src={bluePick[3] ? bluePick[3].image : bluePick[3]}
            className="image"
          ></img>
          {swap !== null && isSwappable() ? (
            <>
              {swap === 3 ? (
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
      <div className="pick-item">
        <div className="name-container"> {blueTeam.sup.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (!isSwappable()) {
              return;
            }
            if (swap === null) {
              setSwap(4);
            } else {
              change(4);
            }
          }}
        >
          <img
            src={bluePick[4] ? bluePick[4].image : bluePick[4]}
            className="image"
          ></img>
          {swap !== null && isSwappable() ? (
            <>
              {swap === 4 ? (
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
    </div>
  );
};
export default BluePick;

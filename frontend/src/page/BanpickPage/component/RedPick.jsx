import React, { useState } from "react";
import "../style.css";

const RedPick = ({ redPick, redTeam, isBlue, isSwapStage, func }) => {
  const [swap, setSwap] = useState(null);
  const change = (idx) => {
    const arr = [...redPick];
    let temp = arr[swap];
    arr[swap] = arr[idx];
    arr[idx] = temp;
    setSwap(null);
    func(arr);
  };

  const isSwappable = () => {
    return !isBlue && isSwapStage;
  };

  return (
    <div className="blue-list">
      <div className="pick-item">
        <div className="name-container"> {redTeam.top.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              setSwap(0);
            } else {
              change(0);
            }
          }}
        >
          <img
            src={redPick[0] ? redPick[0].image : redPick[0]}
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
        <div className="name-container"> {redTeam.jng.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              setSwap(1);
            } else {
              change(1);
            }
          }}
        >
          <img
            src={redPick[1] ? redPick[1].image : redPick[1]}
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
        <div className="name-container"> {redTeam.mid.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              setSwap(2);
            } else {
              change(2);
            }
          }}
        >
          <img
            src={redPick[2] ? redPick[2].image : redPick[2]}
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
        <div className="name-container"> {redTeam.adc.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              setSwap(3);
            } else {
              change(3);
            }
          }}
        >
          <img
            src={redPick[3] ? redPick[3].image : redPick[3]}
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
        <div className="name-container"> {redTeam.sup.nickName}</div>
        <div
          className="image-container"
          onClick={() => {
            if (swap === null) {
              setSwap(4);
            } else {
              change(4);
            }
          }}
        >
          <img
            src={redPick[4] ? redPick[4].image : redPick[4]}
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
export default RedPick;

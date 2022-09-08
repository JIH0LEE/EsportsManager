import React, { useState, useEffect } from "react";
import axios from "axios";
import { API_SERVER } from "../../common";
import { DragDropContext, Droppable, Draggable } from "react-beautiful-dnd";
import { useLocation } from "react-router-dom";
import PlayerCard from "../../component/PlayerCard";
import Auth from "../../hoc/Auth";
import "./style.css";

function EntryChangePage() {
  const location = useLocation();
  const [ids, setIds] = useState(location.state.player);
  const logo = location.state.logo;
  const [swap, setSwap] = useState(null);

  const change = (idx) => {
    // if (!result.destination) return;
    // console.log(e);
    const arr = [...ids];

    let temp = arr[swap];
    arr[swap] = arr[idx];
    arr[idx] = temp;

    // console.log(temp1, temp2);
    setIds(arr);
    setSwap(null);
  };
  return (
    <div className="entry-change-container">
      {ids.map((e, idx) => {
        return (
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
            {swap ? (
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
        );
      })}
    </div>
  );
}
export default Auth(EntryChangePage, null);

import React from "react";
import axios from "axios";
import "./style.css";
import { API_SERVER } from "../../common";

function MainPage() {
  const testApi = () => {
    axios.get(API_SERVER + "/api/test").then((res) => alert(res.data));
  };

  return (
    <div className="main-container">
      <button onClick={testApi}>테스트 버튼</button>
    </div>
  );
}
export default MainPage;

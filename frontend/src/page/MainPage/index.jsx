import React from "react";
import Auth from "../../hoc/Auth";
import "./style.css";

function MainPage() {
  return <div className="main-container"></div>;
}
export default Auth(MainPage, null);

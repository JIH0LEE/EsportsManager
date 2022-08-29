import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { API_SERVER } from "../../common";
import "./style.css";

function LoginPage() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const deleteSpace = (elem) => {
    return elem.replace(" ", "");
  };
  const nameHandler = (e) => {
    setName(deleteSpace(e.target.value));
  };
  const passwordHandler = (e) => {
    setPassword(deleteSpace(e.target.value));
  };

  const submit = () => {
    const body = {
      name,
      password,
    };
    axios
      .post(API_SERVER + "/api/head-coach/login", body)
      .then((res) => {
        navigate("/");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };
  return (
    <div className="login-container">
      <div className="title">로그인</div>
      <div className="name-container">
        <div className="left">닉네임</div>
        <div classNAme="right">
          <input
            className="input"
            placeholder="닉네임을 입력해주세요"
            maxLength={20}
            value={name}
            onChange={nameHandler}
          ></input>
        </div>
      </div>
      <div className="password-container">
        <div className="left">비밀번호</div>
        <div classNAme="right">
          <input
            className="input"
            placeholder="비밀번호를 입력해주세요"
            type="password"
            value={password}
            onChange={passwordHandler}
          ></input>
        </div>
      </div>

      <div className="button-container">
        <div className="button" onClick={submit}>
          로그인
        </div>
      </div>
      <div className="link-container">
        <a href="/register">계정이 없으신가요?</a>
      </div>
    </div>
  );
}
export default LoginPage;

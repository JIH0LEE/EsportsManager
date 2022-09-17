import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { API_SERVER } from "../../common";
import Auth from "../../hoc/Auth";
import "./style.css";

function RegisterPage() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
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
  const passwordCheckHandler = (e) => {
    setPasswordCheck(deleteSpace(e.target.value));
  };
  const submit = () => {
    const body = {
      name,
      password,
      passwordCheck,
    };
    axios
      .post(API_SERVER + "/api/head-coach/register", body)
      .then((res) => {
        navigate("/login");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };

  return (
    <div className="register-container">
      <div className="title">회원가입</div>
      <div className="name-container">
        <div className="left">닉네임</div>
        <div className="right">
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
        <div className="right">
          <input
            className="input"
            placeholder="비밀번호를 입력해주세요"
            type="password"
            value={password}
            onChange={passwordHandler}
          ></input>
        </div>
      </div>
      <div className="password-container">
        <div className="left">비밀번호 확인</div>
        <div className="right">
          <input
            className="input"
            placeholder="다시 비밀번호를 입력해주세요"
            type="password"
            value={passwordCheck}
            onChange={passwordCheckHandler}
          ></input>
        </div>
      </div>
      <div className="button-container">
        <div className="button" onClick={submit}>
          회원가입
        </div>
      </div>
      <div className="link-container">
        <a href="/login">이미 가입하셨나요?</a>
      </div>
    </div>
  );
}
export default Auth(RegisterPage, false);

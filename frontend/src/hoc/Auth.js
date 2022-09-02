import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
function Auth(Componet, option) {
  //option
  // null => 아무나 출입가능
  // true => 로그인한 유저만 출입 가능
  // false => 로그인한 유저는 출입 불가능
  function AuthCheck(props) {
    const { userData } = useSelector((state) => state);
    const navigate = useNavigate();
    useEffect(() => {
      if (!userData.isLogin) {
        if (option) {
          alert("로그인이 필요합니다.");
          navigate("/login");
        }
      } else {
        if (!option) {
          if (option === false) {
            navigate("/");
          }
        }
      }
      // eslint-disable-next-line
    }, []);

    return <Componet />;
  }

  return AuthCheck;
}
export default Auth;

import "./App.css";
import { Routes, Route } from "react-router-dom";
import MainPage from "./page/MainPage";
import BanpickPage from "./page/BanpickPage";
import BattlePage from "./page/BattlePage";
import Header from "./component/Header";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <div className="body-container">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/battle" element={<BattlePage />} />
          <Route path="/banpick" element={<BanpickPage></BanpickPage>} />
        </Routes>
      </div>
    </div>
  );
}

export default App;

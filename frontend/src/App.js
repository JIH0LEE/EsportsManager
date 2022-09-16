import "./App.css";
import { Routes, Route } from "react-router-dom";
import MainPage from "./page/MainPage";
import BanpickPage from "./page/BanpickPage";
import BattlePage from "./page/BattlePage";
import RegisterPage from "./page/RegisterPage";
import LoginPage from "./page/LoginPage";
import CardShowPage from "./page/CardShowPage";
import BookPage from "./page/BookPage";
import MyTeamPage from "./page/MyTeamPage";
import MakeTeamPage from "./page/MakeTeamPage";
import EntryChangePage from "./page/EntryChangePage";
import PersonalSchedulePage from "./page/PersonalSchedulePage";
import GameReadyPage from "./page/GameReadyPage";
import LeagueReadyPage from "./page/LeagueReadyPage";
import Header from "./component/Header";
import LeagueEndPage from "./page/LeagueEndPage";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <div className="body-container">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/battle" element={<BattlePage />} />
          <Route path="/banpick" element={<BanpickPage></BanpickPage>} />
          <Route path="/register" element={<RegisterPage></RegisterPage>} />
          <Route path="/login" element={<LoginPage></LoginPage>} />
          <Route path="/card-show" element={<CardShowPage></CardShowPage>} />
          <Route path="/book" element={<BookPage></BookPage>} />
          <Route path="/my-team" element={<MyTeamPage></MyTeamPage>} />
          <Route path="/make-team" element={<MakeTeamPage></MakeTeamPage>} />
          <Route path="/entry" element={<EntryChangePage></EntryChangePage>} />
          <Route path="/league-end" element={<LeagueEndPage></LeagueEndPage>} />
          <Route
            path="/league-ready"
            element={<LeagueReadyPage></LeagueReadyPage>}
          />
          <Route
            path="/personal-schedule"
            element={<PersonalSchedulePage></PersonalSchedulePage>}
          />
          <Route path="/game-ready" element={<GameReadyPage></GameReadyPage>} />
        </Routes>
      </div>
    </div>
  );
}

export default App;

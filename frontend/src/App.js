import './App.css';
import NestNavbar from "./layout/NestNavbar";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <NestNavbar />
            <Routes>
                <Route path="/" element={<div>Home Page</div>} />
                <Route path="/films" element={<div>Films</div>} />
                <Route path="/watchlist" element={<div>Watchlist</div>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;

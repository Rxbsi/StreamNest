import './App.css';
import NestNavbar from "./layout/NestNavbar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AdminHome from "./admin/AdminHome";
import AddUser from "./admin/user/AddUser";
import EditUser from "./admin/user/EditUser";
import ViewUser from "./admin/user/ViewUser";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <NestNavbar />
            <Routes>
                <Route path="/" element={<div>Home Page</div>} />
                <Route path="/films" element={<div>Films</div>} />
                <Route path="/watchlist" element={<div>Watchlist</div>} />

                <Route path="/admin" >
                    <Route path="home" element={<AdminHome />} />
                    <Route path="adduser" element={<AddUser />} />
                    <Route path="edituser/:id" element={<EditUser />} />
                    <Route path="viewuser/:id" element={<ViewUser />} />
                </Route>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;

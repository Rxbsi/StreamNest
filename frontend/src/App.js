import './App.css';
import NestNavbar from "./layout/NestNavbar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AdminHome from "./admin/user/UserHome";
import AddUser from "./admin/user/AddUser";
import EditUser from "./admin/user/EditUser";
import ViewUser from "./admin/user/ViewUser";
import {AuthProvider} from "./admin/user/auth/AuthContext";
import PrivateRoute from "./admin/user/auth/route/PrivateRoute";
import AdminRoute from "./admin/user/auth/route/AdminRoute";
import Login from "./admin/user/auth/Login";
import UploadHome from "./admin/files/videos/UploadHome";
import Register from "./admin/user/auth/Register";
import MainSite from "./layout/MainSite";

function App() {
    return (
        <div className="App">
            <AuthProvider>
                <BrowserRouter>
                    <NestNavbar />
                    <Routes>
                        <Route path="/login" element={<Login />} />
                        <Route path="/set-password" element={<Register />} />

                        <Route path="/" element={
                            <PrivateRoute>
                                <MainSite />
                            </PrivateRoute>
                        } />

                        <Route path="/films" element={
                            <PrivateRoute>
                                <div>Films</div>
                            </PrivateRoute>
                        } />

                        <Route path="/watchlist" element={
                            <PrivateRoute>
                                <div>Watchlist</div>
                            </PrivateRoute>
                        } />

                        <Route path="/admin" >
                            <Route path="user" element={
                                <AdminRoute>
                                    <AdminHome />
                                </AdminRoute>
                            } />
                            <Route path="adduser" element={
                                <AdminRoute>
                                    <AddUser />
                                </AdminRoute>
                            } />
                            <Route path="edituser/:id" element={
                                <AdminRoute>
                                    <EditUser />
                                </AdminRoute>
                            } />
                            <Route path="viewuser/:id" element={
                                <AdminRoute>
                                    <ViewUser />
                                </AdminRoute>
                            } />

                            <Route path="upload" element={
                                <AdminRoute>
                                    <UploadHome />
                                </AdminRoute>
                            } />
                        </Route>
                    </Routes>
                </BrowserRouter>
            </AuthProvider>
        </div>
    );
}

export default App;
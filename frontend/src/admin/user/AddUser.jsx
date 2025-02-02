import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddUser() {
    let navigate = useNavigate();

    const [user, setUser] = useState({
        username:"",
        name:"",
        lastName:"",
        email:"",
        admin: false
    });

    const{username, name, lastName, email, admin} = user;

    const onInputChange = (e) => {
        setUser({...user, [e.target.name]:e.target.value});
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        try {
            await axios.post("http://localhost:8080/api/user/create", user, config);
            navigate("/admin/user");
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    const navigateToAdmin = () => {
        navigate("/admin/user");
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register User</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">
                                Username
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your username"
                                name="username"
                                value={username}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your name"
                                name="name"
                                value={name}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Lastname" className="form-label">
                                Lastname
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your Last Name"
                                name="lastName"
                                value={lastName}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                E-Mail
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your e-mail address"
                                name="email"
                                value={email}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="admin" className="form-label">
                                Admin User?
                            </label>
                            <br />
                            <input
                                type={"checkbox"}
                                className="form-check-input"
                                name="admin"
                                value={admin}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary" onClick={navigateToAdmin}>
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger mx-2" to="/admin/user">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
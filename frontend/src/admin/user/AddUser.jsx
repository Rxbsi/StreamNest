import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Alert } from "react-bootstrap";

export default function AddUser() {
    let navigate = useNavigate();

    const [error, setError] = useState(null);
    const [user, setUser] = useState({
        username: "",
        name: "",
        lastName: "",
        email: "",
        admin: false
    });
    const { username, name, lastName, email, admin } = user;

    const onInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setUser({
            ...user,
            [name]: type === "checkbox" ? checked : value,
        });
    };

    const isValidEmail = (email) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        if (!isValidEmail(email)) {
            setError("Please use a valid email address");
            return;
        }

        setError(null);

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
            setError("Error on user creation");
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register User</h2>
                    {error && <Alert variant="danger">{error}</Alert>}
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
                                type={"text"} // text instead of mail for better error rendering
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
                                type="checkbox"
                                className="form-check-input"
                                name="admin"
                                checked={admin}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
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

import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditUser() {
    let navigate = useNavigate();
    const { id } = useParams();

    const [user, setUser] = useState({
        username: "",
        name: "",
        lastName: "",
        email: "",
        admin: false,
    });

    const { username, name, lastName, email, admin } = user;

    const onInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setUser({
            ...user,
            [name]: type === "checkbox" ? checked : value,
        });
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
            await axios.put(`http://localhost:8080/api/user/edit/${id}`, user, config);
            navigate("/admin/user");
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    useEffect(() => {
        loadUser();
        // eslint-disable-next-line
    }, []);

    const loadUser = async () => {
        const token = localStorage.getItem("token");

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        try {
            const result = await axios.get(`http://localhost:8080/api/user/byId/${id}`, config);
            setUser(result.data);
        } catch (error) {
            console.error("Error loading user:", error);
        }
    };

    const navigateToAdmin = () => {
        navigate("/admin/user");
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Edit User</h2>

                    <form onSubmit={onSubmit}>
                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">
                                Username
                            </label>
                            <input
                                type="text"
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
                                type="text"
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
                                type="text"
                                className="form-control"
                                placeholder="Enter your Last Name"
                                name="lastName"
                                value={lastName}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                E-mail
                            </label>
                            <input
                                type="text"
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

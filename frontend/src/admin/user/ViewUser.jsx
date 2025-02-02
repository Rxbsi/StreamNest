import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewUser() {
    const [user, setUser] = useState({
        name:"",
        lastName:"",
        username:"",
        email:""
    });

    const {id} = useParams();

    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        const token = localStorage.getItem("token");

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        try {
            const result = await axios.get(`http://localhost:8080/api/user/byId/${id}`, config)
            setUser(result.data);
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">User Details</h2>

                    <div className="card">
                        <div className="card-header">
                            <p>Details of user id : {user.id}</p>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <b>User Name: </b>
                                    {user.username}
                                </li>
                                <li className="list-group-item">
                                    <b>Name: </b>
                                    {user.name}
                                </li>
                                <li className="list-group-item">
                                    <b>Last Name: </b>
                                    {user.lastName}
                                </li>
                                <li className="list-group-item">
                                    <b>Email: </b>
                                    {user.email}
                                </li>
                                <li className="list-group-item">
                                    <b>Admin: </b>
                                    {user.admin ? "Ja" : "Nein"}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Link className="btn btn-primary my-2" to={"/admin/user"}>
                        Back to Home
                    </Link>
                </div>
            </div>
        </div>
    );
}
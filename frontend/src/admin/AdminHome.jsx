import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import {FaPlus} from "react-icons/fa";
import {Button} from "react-bootstrap";

export default function Home() {
    const [users, setUsers] = useState([]);

    const {id} = useParams();

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/api/user/users");
        setUsers(result.data);
    };

    const deleteUser = async (id) => {
        await axios.delete(`http://localhost:8080/api/user/delete/${id}`);
        loadUsers();
    };

    const handleClick = () => {

    }

    return (
        <div className="container">
            <div className="py-4">
                <table className="table border shadow">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Username</th>
                        <th scope="col">Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map((user, index) => (
                        <tr>
                            <td className="align-middle">{user.id}</td>
                            <td className="align-middle">{user.username}</td>
                            <td className="align-middle">{user.name}</td>
                            <td className="align-middle">{user.lastName}</td>
                            <td className="align-middle">{user.email}</td>
                            <td>
                                <Link
                                    className="btn btn-primary mx-2"
                                    to={`/admin/viewuser/${user.id}`}
                                >
                                    View
                                </Link>
                                <Link
                                    className="btn btn-outline-primary mx-2"
                                    to={`/admin/edituser/${user.id}`}
                                >
                                    Edit
                                </Link>
                                <button
                                    className="btn btn-danger mx-2"
                                    onClick={() => deleteUser(user.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colSpan="100%" className="text-center">
                            <Link to={"/admin/adduser"}>
                                <Button variant="link" onClick={handleClick} className="p-0 p-lg-1">
                                    <div className="d-flex justify-content-center align-items-center">
                                        <FaPlus size={15} color="black"/>
                                    </div>
                                </Button>
                            </Link>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    );
}
import React, { useRef, useState } from "react";
import Container from "react-bootstrap/Container";
import { Link, useNavigate } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import {Button, FormControl, InputGroup, NavDropdown} from "react-bootstrap";
import {useAuth} from "../admin/user/auth/AuthContext";

function NestNavbar() {
    const { user, logout } = useAuth();
    const [isVisible, setVisible] = useState(false);
    const navigate = useNavigate();
    const inputRef = useRef(null);

    if (!user) return null;

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    const handleClick = () => {
        setVisible(true);
        setTimeout(() => inputRef.current?.focus(), 0); // Proper Dom Rendering
    };

    const handleBlur = () => {
        setVisible(false)
    };

    const handleKeyDown = (event) => {
        if (event.key === "Enter") {
            const searchValue = inputRef.current.value.trim();
            if (searchValue) {
                navigate("/" + searchValue);
            }
            setVisible(false);
        }
    };

    return (
        <Navbar bg="primary">
            <Container>
                <Navbar.Brand as={Link} to={"/"} className={"text-white text-secondary text-decoration-none"}>
                    StreamNest
                </Navbar.Brand>
                <Nav className="ms-auto">
                    <Nav.Link as={Link} to="/films" className="text-white text-secondary text-decoration-none">
                        Filme
                    </Nav.Link>
                    <Nav.Link as={Link} to="/watchlist" className="text-white text-secondary text-decoration-none">
                        Watchlist
                    </Nav.Link>
                    {!isVisible && (
                        <Button variant="link" onClick={handleClick} className="p-0 p-lg-1">
                            <div className="d-flex justify-content-center align-items-center">
                                <FaSearch size={15} color="white" />
                            </div>
                        </Button>
                    )}
                    {isVisible && (
                        <InputGroup className="ml-2">
                            <FormControl
                                placeholder="Search..."
                                aria-label="Search"
                                ref={inputRef}
                                onKeyDown={handleKeyDown}
                                onBlur={handleBlur}
                            />
                        </InputGroup>
                    )}
                    {user ? (
                        <NavDropdown
                            title={<span className="text-white">{user.username}</span>}
                            id="nav-dropdown"
                        >
                            <NavDropdown.Item as={Link} to="/profile">Profile</NavDropdown.Item>

                            {user?.isAdmin && (
                                <>
                                    <NavDropdown.Divider />
                                    <NavDropdown.Item as={Link} to="/admin/user">User Settings</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/admin/upload">Upload Film</NavDropdown.Item>
                                </>
                            )}

                            <NavDropdown.Divider />
                            <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
                        </NavDropdown>
                    ) : (
                        <Nav.Link as={Link} to="/login" className="text-white">
                            Login
                        </Nav.Link>
                    )}
                </Nav>
            </Container>
        </Navbar>
    );
}

export default NestNavbar;
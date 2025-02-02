import React, { createContext, useState, useContext, useEffect } from 'react';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');

        if (token) {
            try {
                const payloadBase64 = token.split('.')[1];
                if (!payloadBase64) throw new Error("Invalid token format");

                const decodedString = atob(payloadBase64);
                const decoded = JSON.parse(decodedString);

                console.log("Decoded Token:", decoded);

                setUser({
                    username: decoded.username || "Unknown",
                    isAdmin: decoded.isAdmin ?? false,
                });
            } catch (error) {
                console.error('Error decoding token:', error);
                localStorage.removeItem('token');
                setUser(null);
            }
        }

        setLoading(false);
    }, []);

    const login = (token) => {
        localStorage.setItem('token', token);

        try {
            const payloadBase64 = token.split('.')[1];
            if (!payloadBase64) throw new Error("Invalid token format");

            const decodedString = atob(payloadBase64);
            const decoded = JSON.parse(decodedString);

            console.log("Decoded Token on Login:", decoded);

            setUser({
                username: decoded.username || "Unknown",
                isAdmin: decoded.isAdmin ?? false,
            });
        } catch (error) {
            console.error('Error decoding token on login:', error);
            localStorage.removeItem('token');
            setUser(null);
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, loading }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);

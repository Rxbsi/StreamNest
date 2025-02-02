import { Navigate } from 'react-router-dom';
import {useAuth} from "../AuthContext";

const AdminRoute = ({ children }) => {
    const { user, loading } = useAuth();
    console.log(user, loading);



    if (loading) {
        return <div>Loading...</div>;
    }

    if (!user || !user.isAdmin) {
        return <Navigate to="/" />;
    }

    return children;
};

export default AdminRoute;
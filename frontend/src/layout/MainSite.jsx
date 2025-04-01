import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Link} from "react-router-dom";

function MainSite() {
    const [videos, setVideos] = useState([]);

    useEffect(() => {
       loadVideos();

    }, []);

    const loadVideos = async () => {
        const token = localStorage.getItem("token");

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        try {
            const result = await axios.get("http://localhost:8080/api/video/listAll", config);
            setVideos(result.data);
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    return (
        <div>
            {videos.map((video) => (
                <div>
                    {video.title}
                </div>
            ))}
        </div>
    );
}

export default MainSite;
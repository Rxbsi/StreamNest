import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Alert, ProgressBar } from "react-bootstrap";
import GenreSelector from "../genres/GenreSelector";

function UploadFilm() {
    let navigate = useNavigate();

    const [error, setError] = useState(null);
    const [uploading, setUploading] = useState(false);
    const [uploadStatus, setUploadStatus] = useState("");
    const [uploadProgress, setUploadProgress] = useState(0);
    const [file, setFile] = useState(null);

    const [video, setVideo] = useState({
        title: "",
        description: "",
        releaseDate: "",
    });

    const [genres, setGenres] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);
    const { title, description, releaseDate } = video;
    const token = localStorage.getItem("token");

    useEffect(() => {
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        };

        const fetchGenres = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/genres/listAll", config);
                setGenres(response.data);
            } catch (error) {
                console.error("Error fetching genres:", error);
                setError("Error fetching genres");
            }
        };

        fetchGenres();
    }, [token]);

    const onInputChange = (e) => {
        setVideo({ ...video, [e.target.name]: e.target.value });
    };

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!file) {
            setError("Please upload the video first!");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);

        const videoData = {
            title,
            description,
            releaseDate,
            genres: selectedGenres,
        };

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "multipart/form-data",
            },
            withCredentials: true,
            onUploadProgress: (progressEvent) => {
                const percent = Math.round((progressEvent.loaded * 100) / progressEvent.total);
                setUploadProgress(percent);
            },
        };

        setUploading(true);
        setUploadStatus("Uploading...");

        try {
            const videoUploadResponse = await axios.post("http://localhost:8080/api/files/upload", formData, config);
            if (videoUploadResponse.status === 200) {
                await axios.post("http://localhost:8080/api/video/addVideo", videoData, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                navigate(0);
                setUploadStatus("Uploaded successfully!");
                setUploadProgress(0);
            } else {
                setUploadStatus("Error while uploading!");
            }
        } catch (err) {
            console.error("Error uploading video:", err);
            setError("Error while uploading video");
            setUploadProgress(0);
        } finally {
            setUploading(false);
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Upload Video</h2>
                    {error && <Alert variant="danger">{error}</Alert>}
                    {uploadStatus && <Alert variant="info">{uploadStatus}</Alert>}

                    {/* Video Upload */}
                    <div className="mb-3">
                        <label className="form-label">Upload Video</label>
                        <input type="file" accept="video/*" className="form-control" onChange={handleFileChange} />
                    </div>

                    {/* ProgressBar */}
                    {uploading && (
                        <div>
                            <ProgressBar now={uploadProgress} label={`${uploadProgress}%`} />
                        </div>
                    )}

                    {/* Video Metadaten */}
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Title</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Filmtitel eingeben"
                                name="title"
                                value={title}
                                onChange={onInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Description</label>
                            <textarea
                                className="form-control"
                                placeholder="Beschreibung eingeben"
                                name="description"
                                value={description}
                                onChange={onInputChange}
                            ></textarea>
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Release Date</label>
                            <input
                                type="date"
                                className="form-control"
                                name="releaseDate"
                                value={releaseDate}
                                onChange={onInputChange}
                            />
                        </div>

                        {/* Genre Auswahl */}
                        <GenreSelector
                            genres={genres}
                            selectedGenres={selectedGenres}
                            setSelectedGenres={setSelectedGenres}
                        />

                        {/* Buttons */}
                        <div className="d-flex justify-content-center gap-3">
                            <button type="submit" className="btn btn-outline-primary" disabled={uploading}>
                                {uploading ? "Uploading..." : "Save Video"}
                            </button>
                            <button type="button" className="btn btn-danger" onClick={() => navigate("/admin/videos")}>
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default UploadFilm;

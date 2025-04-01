import React from "react";

function GenreSelector({ genres, selectedGenres, setSelectedGenres }) {
    const handleGenreSelect = (genre) => {
        const selectedGenre = genres.find(g => g.genre === genre); // Finde das Genre-Objekt aus der Liste
        if (!selectedGenres.includes(selectedGenre)) {
            setSelectedGenres([...selectedGenres, selectedGenre]);
        }
    };

    const handleGenreRemove = (genre) => {
        const updatedGenres = selectedGenres.filter((g) => g.genre !== genre);
        setSelectedGenres(updatedGenres);
    };

    return (
        <div>
            <div className="mb-3">
                <label className="form-label">Selected Genres</label>
                <div className="d-flex flex-wrap">
                    {selectedGenres.length > 0 ? (
                        selectedGenres.map((genre, index) => (
                            <div key={index} className="badge bg-primary me-2 mb-2 d-flex align-items-center px-3 py-2">
                                <span>{genre.genre}</span>
                                <button
                                    className="btn-close btn-close-white ms-2"
                                    onClick={() => handleGenreRemove(genre.genre)}
                                    aria-label="Remove genre"
                                />
                            </div>
                        ))
                    ) : (
                        <span className="text-muted">NONE</span>
                    )}
                </div>
            </div>

            <div className="mb-3">
                <label className="form-label">Genres</label>
                <select
                    className="form-select"
                    onChange={(e) => handleGenreSelect(e.target.value)}
                    value=""
                >
                    <option value="" disabled>Select Genre</option>
                    {genres.map((genre) => (
                        <option key={genre.id} value={genre.genre}>
                            {genre.genre}
                        </option>
                    ))}
                </select>
            </div>
        </div>
    );
}

export default GenreSelector;

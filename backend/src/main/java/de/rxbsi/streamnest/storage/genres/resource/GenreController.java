package de.rxbsi.streamnest.storage.genres.resource;

import de.rxbsi.streamnest.storage.genres.service.GenreService;
import de.rxbsi.streamnest.storage.genres.service.dto.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping("/listAll")
    public List<Genre> listAllGenres() {
        return service.listAll();
    }

}

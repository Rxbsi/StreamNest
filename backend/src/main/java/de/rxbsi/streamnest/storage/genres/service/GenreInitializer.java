package de.rxbsi.streamnest.storage.genres.service;

import de.rxbsi.streamnest.storage.genres.service.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GenreInitializer implements CommandLineRunner {

    private final GenreRepository genreRepository;

    public GenreInitializer(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        if (genreRepository.count() == 0) {
            Arrays.stream(EGenres.values())
                    .forEach(genre -> {
                        GenreEntity genreEntity = new GenreEntity();
                        genreEntity.setGenre(genre);
                        genreRepository.save(genreEntity);
                    });
        }
    }
}

package de.rxbsi.streamnest.storage.genres.service.impl;

import de.rxbsi.streamnest.storage.genres.mapping.GenreMapper;
import de.rxbsi.streamnest.storage.genres.service.GenreService;
import de.rxbsi.streamnest.storage.genres.service.dto.Genre;
import de.rxbsi.streamnest.storage.genres.service.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper mapper;
    @Autowired
    private GenreRepository repository;

    @Override
    public List<Genre> listAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}

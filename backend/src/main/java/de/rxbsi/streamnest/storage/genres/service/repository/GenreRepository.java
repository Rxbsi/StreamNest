package de.rxbsi.streamnest.storage.genres.service.repository;

import de.rxbsi.streamnest.storage.genres.service.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long>  {

}

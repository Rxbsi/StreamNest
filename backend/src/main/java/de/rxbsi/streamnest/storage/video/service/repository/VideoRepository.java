package de.rxbsi.streamnest.storage.video.service.repository;

import de.rxbsi.streamnest.storage.video.service.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

}

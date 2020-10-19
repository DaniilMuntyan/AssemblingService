package kpi.trspo.restapp.repositories;

import kpi.trspo.restapp.entities.camera.CameraLens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CameraLensRepository extends JpaRepository<CameraLens, UUID> {

}

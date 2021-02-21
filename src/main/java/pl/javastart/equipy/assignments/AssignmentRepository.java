package pl.javastart.equipy.assignments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByAssets_IdAndEndIsNull(Long assetId);
}

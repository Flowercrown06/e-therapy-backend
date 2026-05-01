package az.nexus.etherapybackend.repository;

import az.nexus.etherapybackend.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    // Gələcəkdə filtrasiya üçün bura Query-lər əlavə edəcəyik
}
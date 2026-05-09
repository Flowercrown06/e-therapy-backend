package az.nexus.etherapybackend.repository;

import az.nexus.etherapybackend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
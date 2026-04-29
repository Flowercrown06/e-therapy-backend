package az.nexus.etherapybackend.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
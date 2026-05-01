package az.nexus.etherapybackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "psychologists")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Psychologist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String specialization;
    private Integer experience;
    private String education;
    private String certificates;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String languages;
    private Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

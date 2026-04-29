package az.nexus.etherapybackend.entity;
import az.nexus.etherapybackend.enums.WorkType;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter
public class Vacancy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType type;

    private String description;
    private String requirements;
}

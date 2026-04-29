package az.nexus.etherapybackend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
public class Test {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;
}
package az.nexus.etherapybackend.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter
public class Contact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
}
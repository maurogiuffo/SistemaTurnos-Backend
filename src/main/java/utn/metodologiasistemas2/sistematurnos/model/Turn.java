package utn.metodologiasistemas2.sistematurnos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "turns")
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turn")
    private int id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "turn_date")
    private Date turnDate;

    @Column(name = "turn_time")
    private LocalTime turnTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "user_professional")
    private User professional;

}
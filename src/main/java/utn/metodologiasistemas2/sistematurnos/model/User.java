package utn.metodologiasistemas2.sistematurnos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.metodologiasistemas2.sistematurnos.model.enums.Usertype;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id; //Entity

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String dni;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    private Usertype usertype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Turn> Customer_turns;

    @OneToMany(mappedBy = "professional")
    @JsonIgnore
    private List<Turn> Professional_turns;

}
package utn.metodologiasistemas2.sistematurnos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.metodologiasistemas2.sistematurnos.model.enums.Usertype;

import javax.persistence.*;
import java.util.Date;

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

    @Column(unique = true)
    private String username;

    private String password;


    @Column(name = "created_at")
    private Date createdAt;

    private Usertype usertype;
}
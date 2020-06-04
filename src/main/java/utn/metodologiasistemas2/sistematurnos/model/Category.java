package utn.metodologiasistemas2.sistematurnos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;
    @Column(name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<User> users;
}

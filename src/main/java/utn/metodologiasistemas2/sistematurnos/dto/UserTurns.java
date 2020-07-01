package utn.metodologiasistemas2.sistematurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.metodologiasistemas2.sistematurnos.model.Turn;
import utn.metodologiasistemas2.sistematurnos.projections.TurnProjection;
import utn.metodologiasistemas2.sistematurnos.repository.TurnRepository;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTurns {

    int Id;

    String FirstName;

    String LastName;

    List<TurnProjection> professionalTurns;
}

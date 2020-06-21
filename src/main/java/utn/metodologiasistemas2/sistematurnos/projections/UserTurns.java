package utn.metodologiasistemas2.sistematurnos.projections;

import utn.metodologiasistemas2.sistematurnos.model.Turn;

import java.util.List;

public interface UserTurns {
    int getId();

    String getFirstName();

    String getLastName();

    List<Turn> getProfessional_turns();
}

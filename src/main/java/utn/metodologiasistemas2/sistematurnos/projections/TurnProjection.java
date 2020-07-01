package utn.metodologiasistemas2.sistematurnos.projections;

public interface TurnProjection {

    int getId();

    int getCustomerId();

    int getProfessionalId();

    String getTurnDate();

    String getTurnTime();

    String getCreatedAt();

}

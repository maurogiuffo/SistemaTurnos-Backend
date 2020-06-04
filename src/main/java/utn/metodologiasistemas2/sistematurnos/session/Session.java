package utn.metodologiasistemas2.sistematurnos.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.metodologiasistemas2.sistematurnos.model.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    String token;

    User loggedUser;

    Date lastAction;


}

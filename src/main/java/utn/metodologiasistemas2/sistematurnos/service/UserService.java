package utn.metodologiasistemas2.sistematurnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.ValidationException;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.dto.UserTurns;
import utn.metodologiasistemas2.sistematurnos.repository.UserRepository;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository costumerRepository){
        this.userRepository = costumerRepository;
    }

    public void addUser(User user) {

        user.setCreatedAt(Calendar.getInstance().getTime());

        userRepository.save(user);
    }

    public User getUserById(Integer i) throws  UserNotexistException{
         return  userRepository.findById(i).orElseThrow(()->new UserNotexistException());
    }

    public List<User> getAllUser(String firstName) {
        if(isNull(firstName)) {
            return userRepository.findAll();
        }

        return  userRepository.findByFirstName(firstName);
    }

    public  List<UserTurns>  getAllUserCategory(String categoryName) {

        List<UserTurns> turnsList = new ArrayList<>();


        List<User> listProfessionals = userRepository.findByCategory(categoryName);

        for(User professional:listProfessionals) {
            UserTurns userAux = new UserTurns();
            userAux.setFirstName(professional.getFirstName());
            userAux.setLastName(professional.getLastName());
            userAux.setId(professional.getId());
            userAux.setProfessionalTurns(userRepository.getTurnProjectionById(professional.getId()));
            turnsList.add(userAux);
        }

        return turnsList;
    }

    public User login(String email, String password) throws UserNotexistException, ValidationException{

        if (email == null || password == null) {
            throw new ValidationException("email and password must have a value!");
        }

        User user = userRepository.findByEmailAndPassword(email,password);

        return Optional.ofNullable(user).orElseThrow(() -> new UserNotexistException());
    }
}

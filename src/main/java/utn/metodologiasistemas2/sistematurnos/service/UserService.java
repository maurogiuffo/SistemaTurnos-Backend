package utn.metodologiasistemas2.sistematurnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.ValidationException;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.repository.UserRepository;


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

    public User login(String username, String password) throws UserNotexistException, ValidationException{

        if (username == null || password == null) {
            throw new ValidationException("username and password must have a value!");
        }

        User user = userRepository.findByUsernameAndPassword(username,password);

        return Optional.ofNullable(user).orElseThrow(() -> new UserNotexistException());
    }
}

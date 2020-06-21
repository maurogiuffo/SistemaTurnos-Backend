package utn.metodologiasistemas2.sistematurnos.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.metodologiasistemas2.sistematurnos.dto.LoginRequestDto;
import utn.metodologiasistemas2.sistematurnos.exceptions.InvalidLoginException;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.exceptions.ValidationException;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.service.UserService;
import utn.metodologiasistemas2.sistematurnos.session.SessionManager;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class LoginController {

    UserService userService;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    class LoginResponse{
        public String token;
        public User user;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidLoginException, ValidationException {
        LoginResponse response= new LoginResponse();
        try {
            User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            String token = sessionManager.createSession(user);
            //response = ResponseEntity.ok().headers(createHeaders(token)).build();
            response.token= token;
            response.user= user;
        } catch (UserNotexistException e) {
            throw new InvalidLoginException(e);
        }
        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        responseHeaders.set("token", token);
        return responseHeaders;
    }
}
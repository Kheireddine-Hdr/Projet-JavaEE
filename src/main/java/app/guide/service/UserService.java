package app.guide.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import app.guide.model.User;
import app.guide.web.dto.UserRegistrationDto;



public interface UserService extends UserDetailsService{
	// l'implementation de UserDetailsService  déclarer dan la configuration pour permettre de recuperer les details de l'utilisateur
	User save(UserRegistrationDto registrationDto);
}

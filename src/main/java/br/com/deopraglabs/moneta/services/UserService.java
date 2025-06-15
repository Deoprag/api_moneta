package br.com.deopraglabs.moneta.services;

import br.com.deopraglabs.moneta.domain.User;
import br.com.deopraglabs.moneta.dtos.request.CreateUserRequest;
import br.com.deopraglabs.moneta.exceptions.BRValidationException;
import br.com.deopraglabs.moneta.exceptions.UserNotFoundException;
import br.com.deopraglabs.moneta.repositories.UserRepository;
import br.com.deopraglabs.moneta.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserRequest save(CreateUserRequest userDTO) {
        logger.info("Saving user: " + userDTO);
        validateUserInfo(userDTO);

        return userDTO;
    }

    public User findById(UUID id) {
        logger.info("Finding user by id: " + id);

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public ResponseEntity<?> delete(UUID id) {
        logger.info("Deleting user: " + id);

        if (userRepository.isDeleted(id)) return ResponseEntity.notFound().build();
        userRepository.softDelete(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> resetPassword(UUID id, String password) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setPasswordHash(SecurityUtils.encryptPassword(password));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: " + username);
        final var user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private void validateUserInfo(CreateUserRequest userDTO) {
        final List<String> validations = new ArrayList<>();

        validateBasicFields(userDTO, validations);
        validateUniqueFields(userDTO, validations);

        if (!validations.isEmpty()) {
            throw new BRValidationException(validations);
        }
    }

    private void validateBasicFields(CreateUserRequest userDTO, List<String> validations) {
//        ValidationUtils.checkField(validations, StringUtils.isEmpty(userDTO.getFirstName()), "First name is required");
//        ValidationUtils.checkField(validations, StringUtils.isEmpty(userDTO.getLastName()), "Last name is required");
//        ValidationUtils.checkField(validations, StringUtils.isEmpty(userDTO.getEmail()), "Email is required");
//        ValidationUtils.checkField(validations, userDTO.getBirthDate() == null, "Birth date is required");
//        ValidationUtils.checkField(validations, userDTO.getGender() == '\u0000', "Gender is required");
//        ValidationUtils.checkField(validations, StringUtils.isEmpty(userDTO.getPhoneNumber()), "Phone number is required");
//        ValidationUtils.checkField(validations, (StringUtils.isEmpty(userDTO.getPassword()) && userDTO.getKey() == 0), "Password is required");
    }

    private void validateUniqueFields(CreateUserRequest userDTO, List<String> validations) {
//        if (!ValidationUtils.isEmpty(userDTO.getEmail())
//                && userRepository.findByEmailAndIdNot(userDTO.getEmail(), userDTO.getKey()) != null) {
//            validations.add("Email is already associated with another account");
//        }
//        if (!ValidationUtils.isEmpty(userDTO.getPhoneNumber())
//                && userRepository.findByPhoneNumberAndIdNot(userDTO.getPhoneNumber(), userDTO.getKey()) != null) {
//            validations.add("Phone number is already associated with another account");
//        }
    }
}

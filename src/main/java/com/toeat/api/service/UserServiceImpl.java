package com.toeat.api.service;

import com.toeat.api.Repository.RestaurantRepository;
import com.toeat.api.Repository.UserRepository;
import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public User signIn(String phone, String password) throws EtAuthException {
        Optional<User> user = userRepository.findByPhone(phone);

        if (user.isEmpty()) {
            throw new EtAuthException("User not found");
        } else if (BCrypt.checkpw(password, user.get().getPassword())) {
            return user.get();
        } else {
            throw new EtAuthException("Password incorrect");
        }
    }

    @Override
    public User signUp(String phone, String name, String password, String confirm) throws EtAuthException {

        //TODO VALIDATION
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email != null) email = email.toLowerCase();
//        if (!pattern.matcher(email).matches())
//            throw new EtAuthException("Invalid email format");

        int phoneCnt = userRepository.countByPhone(phone);
        if (phoneCnt > 0)
            throw new EtAuthException(phone + " is already taken");

        if (!password.equals(confirm))
            throw new EtAuthException("Password & Confirm Password does not match.");

//        int emailCnt = userRepository.countByEmail(email);
//        if (emailCnt > 0)
//            throw new EtAuthException("email already in use");

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));//TODO gen salt
        User user = userRepository.save(phone, name, hashPassword, UUID.randomUUID());//TODO UUID GENERATOR
        UUID restaurantId = restaurantRepository.createRest(user.getRestaurantId());

//        log.info("Saving new role {} to the database", role.getName());

        if (!restaurantId.equals(user.getRestaurantId())) {
            //TODO throw exception
        }

        return user;
    }
}

package com.toeat.api.service;

import com.toeat.api.Repository.ClientRepository;
import com.toeat.api.Repository.RestaurantRepository;
import com.toeat.api.Repository.UserRepository;
import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Client;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Client signIn(String phone, String password) throws EtAuthException {
        Optional<Client> user = clientRepository.findByPhone(phone);

        if (user.isEmpty()) {
            throw new EtAuthException("Client not found");
        } else if (BCrypt.checkpw(password, user.get().getPassword())) {
            return user.get();
        } else {
            throw new EtAuthException("Password incorrect");
        }
    }

    @Override
    public Client signUp(String phone, String name, String password, String confirm) throws EtAuthException {

        //TODO VALIDATION
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email != null) email = email.toLowerCase();
//        if (!pattern.matcher(email).matches())
//            throw new EtAuthException("Invalid email format");

        int phoneCnt = clientRepository.countByPhone(phone);
        if (phoneCnt > 0)
            throw new EtAuthException(phone + " is already taken");

        if (!password.equals(confirm))
            throw new EtAuthException("Password & Confirm Password does not match.");

//        int emailCnt = userRepository.countByEmail(email);
//        if (emailCnt > 0)
//            throw new EtAuthException("email already in use");

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));//TODO gen salt
        Client client = clientRepository.save(phone, name, hashPassword, UUID.randomUUID());//TODO UUID GENERATOR
        UUID restaurantId = restaurantRepository.createRest(client.getRestaurantId());

//        log.info("Saving new role {} to the database", role.getName());

        if (!restaurantId.equals(client.getRestaurantId())) {
            //TODO throw exception
        }

        return client;
    }

    @Override
    public Client findByPhone(String phone) throws ModuleException {

        Optional<Client> client = clientRepository.findByPhone(phone);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ModuleException("client not found");
        }
    }
}

package orchowski.tomasz.ecommercedemo.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.ecommercedemo.domain.User;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll(Integer pageNumber, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<User> users = userRepository.findAll(pageRequest);
        List<User> all = users.getContent();
        return all;
    }

    public List<User> findAll(Integer pageNumber, Integer pageSize) {
        return findAll(pageNumber, pageSize, "id");
    }

    public Optional<User> findById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

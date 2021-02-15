package pl.javastart.equipy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService() {
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findByLastName(String name) {
        return userRepository.findAllByLastNameContainsIgnoreCase(name)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        Optional<User> byPesel = userRepository.findByPesel(userDto.getPesel());
        byPesel.ifPresent(user -> {
            throw new DuplicationPeselException();
        });
        User user = userRepository.save(UserMapper.toEntity(userDto));
        return UserMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        User user = findUserById(id);
        return UserMapper.toDto(user);
    }

    public UserDto editUser(UserDto userDto) {

        User userById = findUserById(userDto.getId());
        Optional<User> byPesel = userRepository.findByPesel(userDto.getPesel());
        byPesel.ifPresent(user -> {
                    if (!userById.getPesel().equals(user.getPesel()))
                        throw new DuplicationPeselException();
                }
        );

        User save = userRepository.save(UserMapper.toEntity(userDto));
        return UserMapper.toDto(save);
    }

    private User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Obiekt o takim id nie istnieje");
        return optionalUser.get();
    }


}

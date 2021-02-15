package pl.javastart.equipy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        byPesel.ifPresent(user -> {throw new DuplicationPeselException();});
        User user = userRepository.save(UserMapper.toEntity(userDto));
        return UserMapper.toDto(user);
    }




}

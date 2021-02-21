package pl.javastart.equipy.assignments;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.equip.asserts.Assets;
import pl.javastart.equipy.equip.asserts.AssetsRepository;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private UserRepository userRepository;
    private AssetsRepository assetsRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, UserRepository userRepository, AssetsRepository assetsRepository) {
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.assetsRepository = assetsRepository;
    }

    public AssignmentDto save(AssignmentDto assignmentDto) {
        Optional<Assignment> checkIsUsed = assignmentRepository.findByAssets_IdAndEndIsNull(assignmentDto.getAssetId());
        checkIsUsed.ifPresent(assignment -> {
            throw new AssignmentIsUsedException();
        });
        Assignment assignment = new Assignment();
        assignment.setStart(LocalDateTime.now());
        Assets assets = assetsRepository.findById(assignmentDto.getAssetId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użądzenia o takim id"));
        assignment.setAssets(assets);
        User user = userRepository.findById(assignmentDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użytkownika o takim id"));
        assignment.setUsers(user);
        Assignment save = assignmentRepository.save(assignment);
        return AssignmentMapper.toDto(save);
    }
}

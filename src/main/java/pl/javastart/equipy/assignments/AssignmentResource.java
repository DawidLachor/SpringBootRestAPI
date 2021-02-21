package pl.javastart.equipy.assignments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {

    private AssignmentService assignmentService;

    public AssignmentResource(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public AssignmentDto save(@RequestBody AssignmentDto assignmentDto) {
        if (assignmentDto.getAssetId() == null || assignmentDto.getUserId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id jest null");
        return assignmentService.save(assignmentDto);
    }
}

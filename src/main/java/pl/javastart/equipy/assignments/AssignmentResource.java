package pl.javastart.equipy.assignments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

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

    @PostMapping("/{assignmentId}/end")
    public LocalDateTime recoveryAssignment(@PathVariable Long assignmentId){
        return assignmentService.recovery(assignmentId);
    }
}

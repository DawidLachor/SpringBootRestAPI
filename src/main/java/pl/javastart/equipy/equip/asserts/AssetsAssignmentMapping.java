package pl.javastart.equipy.equip.asserts;

import pl.javastart.equipy.assignments.Assignment;
import pl.javastart.equipy.user.User;

public class AssetsAssignmentMapping {

    public static AssetsAssignmentDto toDto(Assignment assignment){
        AssetsAssignmentDto assignmentDto = new AssetsAssignmentDto();
        assignmentDto.setId(assignment.getId());
        assignmentDto.setStart(assignment.getStart());
        assignmentDto.setEnd(assignment.getEnd());
        User user = assignment.getUsers();
        assignmentDto.setUserId(user.getId());
        assignmentDto.setFirstName(user.getFirstName());
        assignmentDto.setLastName(user.getLastName());
        assignmentDto.setPesel(user.getPesel());
        return assignmentDto;
    }
}

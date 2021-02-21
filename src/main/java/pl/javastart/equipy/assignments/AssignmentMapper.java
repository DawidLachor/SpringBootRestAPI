package pl.javastart.equipy.assignments;

public class AssignmentMapper {

    public static AssignmentDto toDto(Assignment assignment) {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setId(assignmentDto.getId());
        assignmentDto.setStart(assignment.getStart());
        assignmentDto.setEnd(assignment.getEnd());
        assignmentDto.setUserId(assignment.getUsers().getId());
        assignmentDto.setAssetId(assignment.getAssets().getId());
        return assignmentDto;
    }
}

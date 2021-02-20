package pl.javastart.equipy.assignments;

import org.springframework.stereotype.Service;

@Service
public class AssignmentMapping {

    public static AssignmentDto toDto(Assignment assignment){
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setId(assignment.getId());
        assignmentDto.setStart(assignment.getStart());
        assignmentDto.setEnd(assignment.getEnd());
        assignmentDto.setAssetId(assignment.getAssets().getId());
        assignmentDto.setAssetName(assignment.getAssets().getName());
        assignmentDto.setAssetSerialNumber(assignment.getAssets().getSerialNumber());
        return assignmentDto;
    }
}

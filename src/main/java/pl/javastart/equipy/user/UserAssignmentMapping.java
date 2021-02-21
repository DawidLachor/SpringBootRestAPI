package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.assignments.Assignment;

@Service
public class UserAssignmentMapping {

    public static UserAssignmentDto toDto(Assignment assignment){
        UserAssignmentDto userAssignmentDto = new UserAssignmentDto();
        userAssignmentDto.setId(assignment.getId());
        userAssignmentDto.setStart(assignment.getStart());
        userAssignmentDto.setEnd(assignment.getEnd());
        userAssignmentDto.setAssetId(assignment.getAssets().getId());
        userAssignmentDto.setAssetName(assignment.getAssets().getName());
        userAssignmentDto.setAssetSerialNumber(assignment.getAssets().getSerialNumber());
        return userAssignmentDto;
    }
}

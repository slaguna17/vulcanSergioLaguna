package vulcanSergioLaguna.backend.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/api/assignments")
    public ResponseEntity<Void> assignStudentToClassroom(@RequestParam Integer studentId, @RequestParam Integer classroomId) {
        assignmentService.assignStudentToClassroom(studentId, classroomId);
        return ResponseEntity.ok().build();
    }
}


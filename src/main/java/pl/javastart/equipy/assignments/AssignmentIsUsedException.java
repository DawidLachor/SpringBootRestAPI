package pl.javastart.equipy.assignments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Urządzenie jest uzywane")
public class AssignmentIsUsedException extends RuntimeException {
}

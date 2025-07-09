package luis.goes.eval.studentapieval.modules.student.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import luis.goes.eval.studentapieval.modules.student.application.useCase.StudentUseCase;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentRequestDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseWithRoute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {
    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<StudentResponseWithRoute> getById(@PathVariable UUID id, HttpServletRequest request) {
        StudentResponseDto student = studentUseCase.getGetterMethods().getById(id);
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(student, accessedRoute));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<StudentResponseWithRoute> getByName(@PathVariable String name, HttpServletRequest request) {
        StudentResponseDto student = studentUseCase.getGetterMethods().getByName(name);
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(student, accessedRoute));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<StudentResponseWithRoute> getByCpf(@PathVariable String cpf, HttpServletRequest request) {
        StudentResponseDto student = studentUseCase.getGetterMethods().getByCpf(cpf);
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(student, accessedRoute));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<StudentResponseWithRoute> getByEmail(@PathVariable String email, HttpServletRequest request) {
        StudentResponseDto student = studentUseCase.getGetterMethods().getByEmail(email);
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(student, accessedRoute));
    }

    @GetMapping
    public ResponseEntity<StudentResponseWithRoute> getAll(HttpServletRequest request) {
        List<StudentResponseDto> students = studentUseCase.getGetterMethods().getAll();
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(students, accessedRoute));
    }

    @PostMapping
    public ResponseEntity<StudentResponseWithRoute> create(@RequestBody StudentRequestDto dto, HttpServletRequest request) {
        StudentResponseDto createdStudent = studentUseCase.getCreate().create(dto);
        String accessedRoute = request.getRequestURI();
        return ResponseEntity.ok(new StudentResponseWithRoute(createdStudent, accessedRoute));
    }

}
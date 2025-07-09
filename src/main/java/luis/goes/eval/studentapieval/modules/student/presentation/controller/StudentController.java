package luis.goes.eval.studentapieval.modules.student.presentation.controller;

import luis.goes.eval.studentapieval.modules.student.application.useCase.StudentUseCase;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentRequestDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
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
    public ResponseEntity<StudentResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(studentUseCase.getGetterMethods().getById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<StudentResponseDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok().body(studentUseCase.getGetterMethods().getByName(name));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<StudentResponseDto> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(studentUseCase.getGetterMethods().getByCpf(cpf));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<StudentResponseDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(studentUseCase.getGetterMethods().getByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        return ResponseEntity.ok().body(studentUseCase.getGetterMethods().getAll());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentRequestDto dto) {
        return ResponseEntity.ok().body(studentUseCase.getCreate().create(dto));
    }

}
package luis.goes.eval.studentapieval.modules.student.application.useCase;

import lombok.Getter;
import luis.goes.eval.studentapieval.modules.student.application.useCase.create.IStudentCreate;
import luis.goes.eval.studentapieval.modules.student.application.useCase.get.IStudentGet;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StudentUseCase {
    private final IStudentGet getterMethods;
    private final IStudentCreate create;


    public StudentUseCase(IStudentGet getterMethods, IStudentCreate create) {
        this.getterMethods = getterMethods;
        this.create = create;
    }

}
package luis.goes.eval.studentapieval.modules.student.application.useCase;

import lombok.Getter;
import luis.goes.eval.studentapieval.modules.student.application.useCase.changeCpf.IStudentChangeCpf;
import luis.goes.eval.studentapieval.modules.student.application.useCase.changeEmail.IStudentChangeEmail;
import luis.goes.eval.studentapieval.modules.student.application.useCase.changeGrade.IStudentChangeGrade;
import luis.goes.eval.studentapieval.modules.student.application.useCase.changeName.IStudentChangeName;
import luis.goes.eval.studentapieval.modules.student.application.useCase.create.IStudentCreate;
import luis.goes.eval.studentapieval.modules.student.application.useCase.get.IStudentGet;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StudentUseCase {
    private final IStudentGet getterMethods;
    private final IStudentCreate create;
    private final IStudentChangeName changeName;
    private final IStudentChangeCpf changeCpf;
    private final IStudentChangeEmail changeEmail;
    private final IStudentChangeGrade changeGrade;

    public StudentUseCase(IStudentGet getterMethods, IStudentCreate create, IStudentChangeName changeName, IStudentChangeCpf changeCpf,
                          IStudentChangeEmail changeEmail, IStudentChangeGrade changeGrade) {
        this.getterMethods = getterMethods;
        this.create = create;
        this.changeName = changeName;
        this.changeCpf = changeCpf;
        this.changeEmail = changeEmail;
        this.changeGrade = changeGrade;
    }

}
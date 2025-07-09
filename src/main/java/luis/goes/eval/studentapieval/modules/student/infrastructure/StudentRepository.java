package luis.goes.eval.studentapieval.modules.student.infrastructure;

import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByStudentInfo_Name_Name(String name);

    Optional<StudentEntity> findByStudentInfo_Cpf_Cpf(String cpf);

    Optional<StudentEntity> findByStudentInfo_Email_Email(String email);

    List<StudentEntity> findByStudentDateInfo_DeletedAtIsNull(Sort sort);

    @Query("SELECT s.studentInfo.email.email FROM StudentEntity s")
    List<String> findAllEmails();

}
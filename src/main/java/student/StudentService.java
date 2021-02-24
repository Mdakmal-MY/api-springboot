package student;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class StudentService {
	
	private final StudentRepository studentrepository;
	
	@Autowired
	public StudentService(StudentRepository studentrepository) {
		this.studentrepository = studentrepository;
	}
	
	public List<Student> getStudents() {
		return studentrepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentrepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");		
		}
		studentrepository.save(student);
	}
	
	public void deleteStudent(Long id) {
		boolean exist = studentrepository.existsById(id);
		if(!exist) {
			throw new IllegalStateException("Student does not exist");
		}
		studentrepository.deleteById(id);
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentrepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student does not exist"));
		
		if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
	}
}

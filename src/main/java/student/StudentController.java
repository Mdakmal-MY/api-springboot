package student;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

	private final StudentService studentservice;
		
	@Autowired
	public StudentController(StudentService studentservice) {
		this.studentservice = studentservice;
	}
	
	@GetMapping
	public List<Student> getStudents() {
		System.out.println(studentservice.getStudents());
		return studentservice.getStudents();
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentservice.addNewStudent(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId")Long id) {
		studentservice.deleteStudent(id);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId")Long id,
			@RequestParam(required=false)String name,
			@RequestParam(required=false)String email) {
		studentservice.updateStudent(id, name, email);
	}
}

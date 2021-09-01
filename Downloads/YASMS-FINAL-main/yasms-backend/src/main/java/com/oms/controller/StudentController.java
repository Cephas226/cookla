package com.oms.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.oms.model.EmergencyContact;
import com.oms.model.Employee;
import com.oms.model.Institution;
import com.oms.payload.response.MessageResponse;
import com.oms.service.EmergencyContactsService;
import com.oms.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.oms.model.Student;
import com.oms.service.StudentService;

@CrossOrigin(origins="*", maxAge=1800)
@RestController
@RequestMapping("/api")
public class StudentController {

/*	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {

		try {
				List<Student> students = studentService.getAllStudents();

				if (students.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(students, HttpStatus.OK);

			}
			catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {

		Optional<Student> student = studentService.getOneStudent(id);

		if (student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student stu) {

		try {
			Student _student = new Student(stu.getCode(), stu.getLastName(), stu.getFirstName(),
					stu.getGender(), stu.getCourse(), stu.getEmail(), stu.getPhone1(), stu.getPhone2());

			studentService.saveStudent(_student);
			return new ResponseEntity<>(_student, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student s) {

		Optional<Student> student = studentService.getOneStudent(id);
		if (student.isPresent()) {
			Student _stu = student.get();
			_stu.setCode(s.getCode());
			_stu.setLastName(s.getLastName());
			_stu.setFirstName(s.getFirstName());
			_stu.setGender(s.getGender());
			_stu.setCourse(s.getCourse());
			_stu.setEmail(s.getEmail());
			_stu.setPhone1(s.getPhone1());
			_stu.setPhone2(s.getPhone2());

			studentService.updateStudent(_stu);
			return new ResponseEntity<>(_stu, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {

		try {
			studentService.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<Student> deleteAllStudents() {

		try {
			studentService.deleteAllStudents();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
  @Autowired
  private StudentService studentService;

  @Autowired
  EmergencyContactsService emergencyContactsService;

  @Autowired
  private InstitutionService institutionService;

  @GetMapping("/students")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN') or hasRole('USER')")
  public Page<Student> getAllStudents(Pageable pageable){
    return studentService.findAllStudent(pageable);}

  @PostMapping("/students")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
  public ResponseEntity<?> addStudent(@RequestBody Student studentRequest){
    if (studentService.existsByEmail(studentRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }
    Student userStudent = new Student(
    studentRequest.getOldID(),
    studentRequest.getLastName(),
    studentRequest.getMiddleName(),
    studentRequest.getFirstName(),
    studentRequest.getGender(),
    studentRequest.getBirthday(),
    studentRequest.getPhone1(),
    studentRequest.getPhone2(),
    studentRequest.getEmail(),
    studentRequest.getNationality(),
    studentRequest.getIdentificationType(),
    studentRequest.getIdentificationNumber(),
    studentRequest.getAddress(),
    studentRequest.getCity(),
    studentRequest.getCountry(),
    studentRequest.getProfession(),
    studentRequest.getSchoolName(),
    studentRequest.getSchoolPlace(),
    studentRequest.getSchoolType(),
    studentRequest.getNiveauEtude(),
    studentRequest.getSystemEtude(),
    studentRequest.getSerieBac(),
    studentRequest.getMoyenneBac(),
    studentRequest.getEnrollmentDate(),
    studentRequest.getInstitutions()
    );
    Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
    if(!studentRequest.getEmergencyContacts().isEmpty()){
      studentRequest.getEmergencyContacts().forEach(
              emergencyContact -> {
                EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);

                emergencyContacts1.add(ec);
              }
      );};
    /*userStudent.setEmergencyContacts(emergencyContacts1);
    Set<Institution> institutionSet = new HashSet<>();
    if(!studentRequest.getInstitutions().isEmpty()){
      studentRequest.getInstitutions().forEach(
              inst -> {
                Institution it= institutionService.saveInstitution(inst);
                institutionSet.add(it);
              }
      );
      studentRequest.setInstitutions(institutionSet);
    }*/
    studentService.saveStudent(userStudent);
    return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
    //return studentService.saveStudent(userStudent);
  }

  @PutMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public Optional<Student> updateStudent (@RequestBody Student studentRequest, @PathVariable ("id") Long Id){
    return studentService.findStudentByCode(Id).map(student -> {
      student.setOldID(studentRequest.getOldID());
      student.setLastName(studentRequest.getLastName());
      student.setMiddleName(studentRequest.getMiddleName());
      student.setFirstName(studentRequest.getFirstName());
      student.setGender(studentRequest.getGender());
      student.setBirthday(studentRequest.getBirthday());
      student.setPhone1(studentRequest.getPhone1());
      student.setPhone2(studentRequest.getPhone2());
      student.setEmail(studentRequest.getEmail());
      student.setNationality(studentRequest.getNationality());
      student.setIdentificationType(studentRequest.getIdentificationType());
      student.setIdentificationNumber(studentRequest.getIdentificationNumber());
      student.setAddress(studentRequest.getAddress());
      student.setCity(studentRequest.getCity());
      student.setCountry(studentRequest.getCountry());
      student.setProfession(studentRequest.getProfession());
      student.setSchoolName(studentRequest.getSchoolName());
      student.setSchoolPlace(studentRequest.getSchoolPlace());
      student.setSchoolType(studentRequest.getSchoolType());
      student.setNiveauEtude(studentRequest.getNiveauEtude());
      student.setSystemEtude(studentRequest.getSystemEtude());
      student.setSerieBac(studentRequest.getSerieBac());
      student.setMoyenneBac(studentRequest.getMoyenneBac());
      student.setInstitutions( studentRequest.getInstitutions());
      student.setEnrollmentDate(studentRequest.getEnrollmentDate());
      Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
      if(!studentRequest.getEmergencyContacts().isEmpty()){
        studentRequest.getEmergencyContacts().forEach(
                emergencyContact -> {
                  EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);

                  emergencyContacts1.add(ec);
                }
        );};
      student.setEmergencyContacts(emergencyContacts1);

      Set<Institution> institutionSet = new HashSet<>();
      if(!studentRequest.getInstitutions().isEmpty()){
        studentRequest.getInstitutions().forEach(
                inst -> {
                  Institution it= institutionService.saveInstitution(inst);
                  institutionSet.add(it);
                }
        );
        student.setInstitutions(institutionSet);
      }
      return studentService.saveStudent(student);
    });
  }
  @DeleteMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public void deleteStudent(@PathVariable ("id")Long Id){
     studentService.deleteStudent(Id);
  }

  @GetMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public Optional<Student> selectOneStudent(@PathVariable ("id") Long Id){
    return studentService.findStudentByCode(Id);
  }
};

package com.oms.controller;

import java.util.*;

import com.oms.model.*;
import com.oms.payload.request.CreationRequest;
import com.oms.payload.response.MessageResponse;
import com.oms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class EmployeeController {
  private JavaMailSender javaMailSender;
  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private EmergencyContactsService emergencyContactsService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private InstitutionService institutionService;

  @Autowired
  RoleService roleService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  PasswordEncoder passwordDecoder;

  @Autowired
  private MailService notificationService;

  @Autowired
  private Employee employee;



  @Autowired
  public void MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }


  /*Ici les autorisé creé des Employees*/

  /*Selectionnez tous les employees*/
  @GetMapping("/employees")
  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Page<Employee> ListUsers(Pageable pageable) {
    return employeeService.findAll(pageable);
  }
  @GetMapping("/emp/{status}")
  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
  List <Employee> findEmpByStatus(@PathVariable("status") Boolean status){
    return employeeService.findByStatus(status);
  };
  /*Selectionnez un employee*/
  @GetMapping("/employees/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Optional<Employee>  getEmployeeById(@PathVariable("id") Long id) {
    return employeeService.findById(id);
  }
  /*Update un employee*/
  @PutMapping("/employees/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Optional<Employee> updateEmployee(@RequestBody CreationRequest employeeRequest, @PathVariable ("id") Long id) {
    return employeeService.findById(id).map(employee->{
      employee.setFirstName(employeeRequest.getFirstName());
      employee.setLastName(employeeRequest.getLastName());
      employee.setGender(employeeRequest.getGender());
      employee.setJobRole(employeeRequest.getJobRole());
      employee.setJobType(employeeRequest.getJobType());
      employee.setDepartment(employeeRequest.getDepartment());
      employee.setEmail(employeeRequest.getEmail());
      employee.setBusinessPhone(employeeRequest.getBusinessPhone());
      employee.setPrivatePhone(employeeRequest.getPrivatePhone());
      employee.setWorkLocation(employeeRequest.getWorkLocation());
      employee.setStatus(employeeRequest.getStatus());
      employee.setPassword(employee.getPassword());
      employee.setLinkedinLink(employeeRequest.getLinkedinLink());
      employee.setFacebookLink(employeeRequest.getFacebookLink());
      employee.setYoutubeLink(employeeRequest.getYoutubeLink());
      employee.setXingLink(employeeRequest.getXingLink());
      employee.setAddress(employeeRequest.getAddress());
      employee.setBirthday(employeeRequest.getBirthday());
      employee.setNationality(employeeRequest.getNationality());
      employee.setCountry(employeeRequest.getCountry());
      employee.setCity(employeeRequest.getCity());

      Set<String> strRoles = employeeRequest.getRole();
      Set<Role> roles = new HashSet<>();
      if (strRoles == null) {
        Role modRole = roleService.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(modRole);
      } else {
        strRoles.forEach(role -> {
          System.out.println(role);
          switch (role) {
            case "user":
              Role modRole = roleService.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(modRole);
              break;
          }
          switch (role) {
            case "moderator":
              Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(modRole);
              break;
          }
        });
      }
      System.out.println(employee);
         employee.setRoles(roles);
      Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
      if(!employeeRequest.getEmergencyContacts().isEmpty()){
        employeeRequest.getEmergencyContacts().forEach(
                emergencyContact -> {
                  EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);
                  emergencyContacts1.add(ec);
                }
        );
        employee.setEmergencyContacts(emergencyContacts1);
      }

      Set<Skill> skillSet = new HashSet<>();
      if(!employeeRequest.getSkills().isEmpty()){
        employeeRequest.getSkills().forEach(
                skill -> {
                  Skill sk= skillService.saveSkill(skill);
                  skillSet.add(sk);
                }
        );
        employee.setSkills(skillSet);
      }
      Set<Institution> institutionSet = new HashSet<>();
      if(!employeeRequest.getInstitution().isEmpty()){
        employeeRequest.getInstitution().forEach(
                inst -> {
                  Institution it= institutionService.saveInstitution(inst);
                  institutionSet.add(it);
                }
        );
        employee.setInstitutions(institutionSet);
      }
        return employeeService.saveEmployee(employee);
    });
  }
  @PatchMapping("/employees/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Optional<Employee> patchEmployee(@RequestBody CreationRequest employeeRequest, @PathVariable ("id") Long id) {
    return employeeService.findById(id).map(employee->{
      employee.setStatus(employeeRequest.getStatus());
      return employeeService.saveEmployee(employee);
    });
  }
  /*Ici l'admin cree un user avec role moderateur*/
  @PostMapping("/createModerator")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> createModerator(@RequestBody CreationRequest creationRequest) {
    if (employeeService.existsByEmail(creationRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
    }
    Employee userRegistered = new Employee(
    creationRequest.getLastName(),
    creationRequest.getFirstName(),
    creationRequest.getGender(),
    creationRequest.getBirthday(),
    creationRequest.getBusinessPhone(),
    creationRequest.getPrivatePhone(),
    creationRequest.getEmail(),
    creationRequest.getNationality(),
    creationRequest.getStatus(),
    creationRequest.getLinkedinLink(),
    creationRequest.getFacebookLink(),
    creationRequest.getYoutubeLink(),
    creationRequest.getXingLink(),
    creationRequest.getAddress(),
    creationRequest.getCity(),
    creationRequest.getCountry(),
    creationRequest.getDepartment(),
    creationRequest.getJobType(),
    creationRequest.getJobType(),
    creationRequest.getWorkLocation(),
    encoder.encode(creationRequest.getFirstName()));
     // creationRequest.getHistorique();

    Set<String> strRoles = creationRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(modRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "moderator":
            Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
            break;
        }
      });
    }
    userRegistered.setRoles(roles);
    System.out.println(userRegistered.toString());
    employeeService.saveEmployee(userRegistered);
    return ResponseEntity.ok(new MessageResponse("Moderator registered successfully!"));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("moderateur/{id}")
  public Optional<Employee> updateModerator(@RequestBody Employee moderatorRequest,@PathVariable ("id") Long id) {
    return employeeService.findById(id).map(moderator -> {
      moderator.setFirstName(moderatorRequest.getFirstName());
      moderator.setLastName(moderatorRequest.getLastName());
      moderator.setGender(moderatorRequest.getGender());
      moderator.setJobRole(moderatorRequest.getJobRole());
      moderator.setDepartment(moderatorRequest.getDepartment());
      moderator.setEmail(moderatorRequest.getEmail());
      moderator.setBusinessPhone(moderatorRequest.getEmail());
      moderator.setPrivatePhone(moderatorRequest.getEmail());
      moderator.setWorkLocation(moderatorRequest.getEmail());
      moderator.setStatus(moderatorRequest.getStatus());
      moderator.setPassword(moderatorRequest.getPassword());
      moderator.setRoles(moderatorRequest.getRoles());
      return employeeService.saveEmployee(moderatorRequest);
    });
  }
@PostMapping("/employees")
@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
public Employee createEmployee(@RequestBody CreationRequest creationRequest) {
  System.out.println(creationRequest.toString());
  if (employeeService.existsByEmail(creationRequest.getEmail())) {
    return null;
  }
  // Create new user's account
  Employee userRegistered = new Employee(
          creationRequest.getLastName(),
          creationRequest.getFirstName(),
          creationRequest.getGender(),
          creationRequest.getBirthday(),
          creationRequest.getBusinessPhone(),
          creationRequest.getPrivatePhone(),
          creationRequest.getEmail(),
          creationRequest.getNationality(),
          creationRequest.getStatus(),
          creationRequest.getLinkedinLink(),
          creationRequest.getFacebookLink(),
          creationRequest.getYoutubeLink(),
          creationRequest.getXingLink(),
          creationRequest.getAddress(),
          creationRequest.getCity(),
          creationRequest.getCountry(),
          creationRequest.getDepartment(),
          creationRequest.getJobType(),
          creationRequest.getJobRole(),
          creationRequest.getWorkLocation(),
          encoder.encode(creationRequest.getFirstName()));

  Set<String> strRoles = creationRequest.getRole();
  Set<Role> roles = new HashSet<>();

  if (strRoles == null) {
    Role userRole = roleService.findByName(ERole.ROLE_USER)
      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
  } else {
    strRoles.forEach(role -> {
      System.out.println(role);
      switch (role) {
        case "admin":
          Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "moderator":
          Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleService.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      }
    });
  }
  userRegistered.setRoles(roles);
  Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
  if(!creationRequest.getEmergencyContacts().isEmpty()){
    creationRequest.getEmergencyContacts().forEach(
            emergencyContact -> {
              EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);
              emergencyContacts1.add(ec);
            }
    );
    userRegistered.setEmergencyContacts(emergencyContacts1);
  }

  Set<Skill> skillSet = new HashSet<>();
  if(!creationRequest.getSkills().isEmpty()){
    creationRequest.getSkills().forEach(
            skill -> {
              Skill sk= skillService.saveSkill(skill);
              skillSet.add(sk);
            }
    );
    userRegistered.setSkills(skillSet);
  }
  Set<Institution> institutionSet = new HashSet<>();
  if(!creationRequest.getInstitution().isEmpty()){
    creationRequest.getInstitution().forEach(
            inst -> {
              Institution it= institutionService.saveInstitution(inst);
              institutionSet.add(it);
            }
    );
    employee.setInstitutions(institutionSet);
  }
  employeeService.saveEmployee(userRegistered);
  return userRegistered;
}

  @PatchMapping("/emailEmployee")
  public String postEmail(@RequestBody String employeeEmail){
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int length = 7;
    for(int i = 0; i < length; i++) {
      int index = random.nextInt(alphabet.length());
      char randomChar = alphabet.charAt(index);
      sb.append(randomChar);
    }
    String newPassword = sb.toString();
    System.out.println(newPassword);
    employeeService.findByEmail("tot@gmail.com")
      .map(emp ->{
         emp.setPassword(encoder.encode(newPassword));
          return employeeService.saveEmployee(emp);
        }
      );
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(employeeEmail);
    mail.setSubject("Test OMS password recovey");
    mail.setText("Your new password is "+newPassword);
    javaMailSender.send(mail);

      return "Email sended "+newPassword;
  }
}

package com.oms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.oms.model.*;

import com.oms.payload.request.CreationRequest;
import com.oms.payload.request.LoginRequest;
import com.oms.payload.response.JwtResponse;
import com.oms.payload.response.MessageResponse;
import com.oms.security.jwt.JwtUtils;
import com.oms.security.services.EmployeeDetailsImpl;
import com.oms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/auth")
public class SuperAdminController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  EmergencyContactsService emergencyContactsService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private InstitutionService institutionService;

  @Autowired
  RoleService roleService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/loginUser")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
    System.out.println(loginRequest.toString());
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
    List<String> roles = employeeDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(
      new JwtResponse(
            jwt,
            employeeDetails.getId(),
            employeeDetails.getFirstName(),
            employeeDetails.getLastName(),
            employeeDetails.getGender(),
            employeeDetails.getEmail(),
            roles
      )
    );
  }

  @PostMapping("/createSuperAdmin")
  public ResponseEntity<?> createSuperAdmin(@RequestBody CreationRequest creationRequest) {
    System.out.println(creationRequest.toString());
    if (employeeService.existsByEmail(creationRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
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
            creationRequest.getJobType(),
            creationRequest.getWorkLocation(),
            encoder.encode(creationRequest.getFirstName()));
     // creationRequest.getHistorique());

    Set<String> strRoles = creationRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleService.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "superadmin":
            Role superAdmin = roleService.findByName(ERole.ROLE_SUPER_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(superAdmin);
        }
      });
    }
    userRegistered.setRoles(roles);
    System.out.println(userRegistered.toString());
    employeeService.saveEmployee(userRegistered);
    return ResponseEntity.ok(new MessageResponse("Super Admin created successfully!"));
  }

  @PostMapping("/dispachRole")
  @PreAuthorize("hasRole('SUPER_ADMIN')")
  public ResponseEntity<?> dispachRole(@RequestBody CreationRequest creationRequest) {
    System.out.println(creationRequest.toString());
    if (employeeService.existsByEmail(creationRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
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
            creationRequest.getJobType(),
            creationRequest.getWorkLocation(),
            encoder.encode(creationRequest.getFirstName()));
      //creationRequest.getHistorique());

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
    System.out.println(userRegistered.toString());
    Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
    if(!creationRequest.getEmergencyContacts().isEmpty()){
      creationRequest.getEmergencyContacts().forEach(
              emergencyContact -> {
                EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);

                emergencyContacts1.add(ec);
              }
      );
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
        userRegistered.setInstitutions(institutionSet);
      }
    }
    employeeService.saveEmployee(userRegistered);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}


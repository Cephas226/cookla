package com.oms.service;

import com.oms.model.PlannedCourse;
import com.oms.model.Teachers;
import com.oms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    public Optional<Teachers> findById(Long id){
        return teacherRepository.findById(id);
    }
    public Teachers saveTeacher(Teachers teachers){
        return teacherRepository.save(teachers);
    }
    public Page<Teachers> findAll(Pageable pageable) {
        return teacherRepository.findAll( pageable);
    }
    public void deleteTeacher(Long Id){
        teacherRepository.deleteById(Id);
    }
}

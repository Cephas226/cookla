package com.oms.service;

import com.oms.model.PlannedCourse;
import com.oms.repository.CoursePlannedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursePlannedService {

    @Autowired
    private CoursePlannedRepository coursePlannedRepository;

    public Optional<PlannedCourse> findById(Long id){
        return coursePlannedRepository.findById(id);
    }
    /*public Optional<PlannedCourse>findByIdAndCousesId(Long id, Long courseId){
        return coursePlannedRepository.findByIdAndCousesId(id, courseId);
    }*/
    public PlannedCourse savePlannedCourse(PlannedCourse plannedCourse){
        return coursePlannedRepository.save(plannedCourse);
    }
    public Page<PlannedCourse> findAll(Pageable pageable) {
        return coursePlannedRepository.findAll( pageable);
    }
    public void deletePlannedCourse(Long Id){
        coursePlannedRepository.deleteById(Id);
    }
}

package com.oms.service;

import com.oms.model.Room;
import com.oms.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;
    public Optional<Room> findById(Long id){
        return classroomRepository.findById(id);
    }
    public Room saveClassroom(Room room){
        return classroomRepository.save(room);
    }
    public Page<Room> findAll(Pageable pageable) {
        return classroomRepository.findAll( pageable);
    }
    public void deleteClassroom(Long Id){
        classroomRepository.deleteById(Id);
    }
}

package com.example.todolist.service;


/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 3/5/2025 3:26 PM
@Last Modified 3/5/2025 3:26 PM
Version 1.0
*/


import com.example.todolist.entity.Checklist;
import com.example.todolist.entity.User;
import com.example.todolist.repository.ChecklistRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {
    @Autowired private ChecklistRepository checklistRepository;
    @Autowired private UserRepository userRepository;

    public List<Checklist> getAllChecklists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> checklistRepository.findByUserId(value.getId())).orElse(null);
    }

    public Checklist createChecklist(String name, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Checklist checklist = new Checklist();
            checklist.setName(name);
            checklist.setUser(user.get());
            return checklistRepository.save(checklist);
        }
        return null;
    }

    public boolean deleteChecklist(Long checklistId) {
        if (checklistRepository.existsById(checklistId)) {
            checklistRepository.deleteById(checklistId);
            return true;
        }
        return false;
    }
}
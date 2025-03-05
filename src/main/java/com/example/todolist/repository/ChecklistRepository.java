package com.example.todolist.repository;


/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 3/5/2025 3:22 PM
@Last Modified 3/5/2025 3:22 PM
Version 1.0
*/


import com.example.todolist.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    List<Checklist> findByUserId(Long userId);
}

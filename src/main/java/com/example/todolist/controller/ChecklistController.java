package com.example.todolist.controller;


/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 3/5/2025 3:27 PM
@Last Modified 3/5/2025 3:27 PM
Version 1.0
*/


import com.example.todolist.entity.Checklist;
import com.example.todolist.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {
    @Autowired private ChecklistService checklistService;

    @GetMapping
    public ResponseEntity<List<Checklist>> getAllChecklists() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(checklistService.getAllChecklists(username));
    }

    @PostMapping
    public ResponseEntity<Checklist> createChecklist(@RequestBody Map<String, String> request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(checklistService.createChecklist(request.get("name"), username));
    }

    @DeleteMapping("/{checklistId}")
    public ResponseEntity<?> deleteChecklist(@PathVariable Long checklistId) {
        return checklistService.deleteChecklist(checklistId) ?
                ResponseEntity.ok().body("Checklist deleted successfully") :
                ResponseEntity.badRequest().body("Checklist not found");
    }
}

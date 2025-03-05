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


import com.example.todolist.entity.ChecklistItem;
import com.example.todolist.service.ChecklistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checklist/{checklistId}/item")
public class ChecklistItemController {
    @Autowired private ChecklistItemService checklistItemService;

    @GetMapping
    public ResponseEntity<List<ChecklistItem>> getChecklistItems(@PathVariable Long checklistId) {
        return ResponseEntity.ok(checklistItemService.getChecklistItems(checklistId));
    }

    @PostMapping
    public ResponseEntity<ChecklistItem> createChecklistItem(@PathVariable Long checklistId, @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(checklistItemService.createChecklistItem(checklistId, request.get("itemName")));
    }

    @DeleteMapping("/{checklistItemId}")
    public ResponseEntity<?> deleteChecklistItem(@PathVariable Long checklistItemId) {
        return checklistItemService.deleteChecklistItem(checklistItemId) ?
                ResponseEntity.ok().body("Item deleted successfully") :
                ResponseEntity.badRequest().body("Item not found");
    }

    @PutMapping("/{checklistItemId}")
    public ResponseEntity<ChecklistItem> updateChecklistItemStatus(@PathVariable Long checklistItemId, @RequestBody Map<String, Boolean> request) {
        return ResponseEntity.ok(checklistItemService.updateChecklistItemStatus(checklistItemId, request.get("completed")));
    }

    @PutMapping("/rename/{checklistItemId}")
    public ResponseEntity<ChecklistItem> renameChecklistItem(@PathVariable Long checklistItemId, @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(checklistItemService.renameChecklistItem(checklistItemId, request.get("itemName")));
    }
}
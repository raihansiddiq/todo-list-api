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
import com.example.todolist.entity.ChecklistItem;
import com.example.todolist.repository.ChecklistItemRepository;
import com.example.todolist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistItemService {
    @Autowired private ChecklistItemRepository checklistItemRepository;
    @Autowired private ChecklistRepository checklistRepository;

    public List<ChecklistItem> getChecklistItems(Long checklistId) {
        return checklistItemRepository.findByChecklistId(checklistId);
    }

    public ChecklistItem createChecklistItem(Long checklistId, String itemName) {
        Optional<Checklist> checklist = checklistRepository.findById(checklistId);
        if (checklist.isPresent()) {
            ChecklistItem item = new ChecklistItem();
            item.setItemName(itemName);
            item.setCompleted(false);
            item.setChecklist(checklist.get());
            return checklistItemRepository.save(item);
        }
        return null;
    }

    public boolean deleteChecklistItem(Long checklistItemId) {
        if (checklistItemRepository.existsById(checklistItemId)) {
            checklistItemRepository.deleteById(checklistItemId);
            return true;
        }
        return false;
    }

    public ChecklistItem updateChecklistItemStatus(Long checklistItemId, boolean completed) {
        Optional<ChecklistItem> item = checklistItemRepository.findById(checklistItemId);
        if (item.isPresent()) {
            item.get().setCompleted(completed);
            return checklistItemRepository.save(item.get());
        }
        return null;
    }

    public ChecklistItem renameChecklistItem(Long checklistItemId, String newName) {
        Optional<ChecklistItem> item = checklistItemRepository.findById(checklistItemId);
        if (item.isPresent()) {
            item.get().setItemName(newName);
            return checklistItemRepository.save(item.get());
        }
        return null;
    }
}

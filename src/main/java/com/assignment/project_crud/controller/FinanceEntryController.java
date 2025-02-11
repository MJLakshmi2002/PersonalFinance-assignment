package com.assignment.project_crud.controller;
import  com.assignment.project_crud.model.FinanceEntry;
import  com.assignment.project_crud.service.FinanceEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/finance")
@CrossOrigin(origins = "*")
public class FinanceEntryController {

    private final FinanceEntryService service;

    public FinanceEntryController(FinanceEntryService service) {
        this.service = service;
    }

    @GetMapping
    public List<FinanceEntry> getAllEntries() {
        return service.getAllEntries();
    }

    @PostMapping
    public ResponseEntity<FinanceEntry> addEntry(@RequestBody FinanceEntry entry) {
        return ResponseEntity.ok(service.addEntry(entry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceEntry> updateEntry(@PathVariable Long id, @RequestBody FinanceEntry entry) {
        return ResponseEntity.ok(service.updateEntry(id, entry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        service.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/type/{type}")
    public List<FinanceEntry> filterByType(@PathVariable String type) {
        return service.filterByType(type);
    }

    @GetMapping("/filter/category/{category}")
    public List<FinanceEntry> filterByCategory(@PathVariable String category) {
        return service.filterByCategory(category);
    }

    @GetMapping("/filter/date")
    public List<FinanceEntry> filterByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return service.filterByDateRange(startDate, endDate);
    }
}


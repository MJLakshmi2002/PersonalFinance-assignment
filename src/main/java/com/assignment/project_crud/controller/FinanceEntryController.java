package com.assignment.project_crud.controller;

import com.assignment.project_crud.model.FinanceEntry;
import com.assignment.project_crud.service.FinanceEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/finance")
@CrossOrigin(origins = "*")
public class FinanceEntryController {

    private final FinanceEntryService service;

    public FinanceEntryController(FinanceEntryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FinanceEntry>> getAllEntries() {
        List<FinanceEntry> entries = service.getAllEntries();
        return ResponseEntity.ok(entries);
    }

    @PostMapping
    public ResponseEntity<?> addEntry(@RequestBody FinanceEntry entry) {
        if (entry == null || entry.getAmount() <= 0 || entry.getCategory() == null || entry.getType() == null) {
            return ResponseEntity.badRequest().body("Invalid input: amount, category, and type are required.");
        }
        FinanceEntry savedEntry = service.addEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable Long id, @RequestBody FinanceEntry entry) {
        FinanceEntry updatedEntry = service.updateEntry(id, entry);
        if (updatedEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
        }
        return ResponseEntity.ok(updatedEntry);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
        }
        service.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/type/{type}")
    public ResponseEntity<List<FinanceEntry>> filterByType(@PathVariable String type) {
        return ResponseEntity.ok(service.filterByType(type));
    }

    @GetMapping("/filter/category/{category}")
    public ResponseEntity<List<FinanceEntry>> filterByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.filterByCategory(category));
    }

    @GetMapping("/filter/date")
    public ResponseEntity<List<FinanceEntry>> filterByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return ResponseEntity.ok(service.filterByDateRange(start, end));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}



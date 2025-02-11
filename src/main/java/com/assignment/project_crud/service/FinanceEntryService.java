package com.assignment.project_crud.service;
import com.assignment.project_crud.model.FinanceEntry;
import com.assignment.project_crud.repository.FinanceEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinanceEntryService {

    private final FinanceEntryRepository repository;

    public FinanceEntryService(FinanceEntryRepository repository) {
        this.repository = repository;
    }

    public List<FinanceEntry> getAllEntries() {
        return repository.findAll();
    }

    public FinanceEntry addEntry(FinanceEntry entry) {
        return repository.save(entry);
    }

    public FinanceEntry updateEntry(Long id, FinanceEntry updatedEntry) {
        return repository.findById(id)
                .map(entry -> {
                    entry.setAmount(updatedEntry.getAmount());
                    entry.setCategory(updatedEntry.getCategory());
                    entry.setDate(updatedEntry.getDate());
                    entry.setDescription(updatedEntry.getDescription());
                    entry.setType(updatedEntry.getType());
                    return repository.save(entry);
                })
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }

    public List<FinanceEntry> filterByType(String type) {
        return repository.findByType(type);
    }

    public List<FinanceEntry> filterByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<FinanceEntry> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findByDateBetween(startDate, endDate);
    }
}

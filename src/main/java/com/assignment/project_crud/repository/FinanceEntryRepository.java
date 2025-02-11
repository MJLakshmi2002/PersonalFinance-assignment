package com.assignment.project_crud.repository;
import com.assignment.project_crud.model.FinanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinanceEntryRepository extends JpaRepository<FinanceEntry, Long> {
    List<FinanceEntry> findByType(String type);
    List<FinanceEntry> findByCategory(String category);
    List<FinanceEntry> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

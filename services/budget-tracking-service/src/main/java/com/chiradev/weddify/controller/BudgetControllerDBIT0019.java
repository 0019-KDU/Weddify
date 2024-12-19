package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.BudgetDTOBIT0019;
import com.chiradev.weddify.dto.ExpenseDTOBIT0019;
import com.chiradev.weddify.service.BudgetServiceDBIT0019;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetControllerDBIT0019 {

    private final BudgetServiceDBIT0019 budgetService;

    @PostMapping
    public ResponseEntity<BudgetDTOBIT0019> setBudget(@Valid @RequestBody BudgetDTOBIT0019 budgetDTO) {
        BudgetDTOBIT0019 createdBudget = budgetService.setBudget(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @PostMapping("/{budgetId}/expenses")
    public ResponseEntity<ExpenseDTOBIT0019> addExpense(@PathVariable Long budgetId,
                                                        @Valid @RequestBody ExpenseDTOBIT0019 expenseDTO) {
        ExpenseDTOBIT0019 createdExpense = budgetService.addExpense(budgetId, expenseDTO);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/{budgetId}/summary")
    public ResponseEntity<BudgetDTOBIT0019> getBudgetSummary(@PathVariable Long budgetId) {
        BudgetDTOBIT0019 budgetSummary = budgetService.getBudgetSummary(budgetId);
        return ResponseEntity.ok(budgetSummary);
    }

    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<ExpenseDTOBIT0019> getExpenseDetails(@PathVariable Long expenseId) {
        ExpenseDTOBIT0019 expenseDetails = budgetService.getExpenseDetails(expenseId);
        return ResponseEntity.ok(expenseDetails);
    }

    @GetMapping("/{budgetId}/expenses")
    public ResponseEntity<List<ExpenseDTOBIT0019>> getAllExpenses(@PathVariable Long budgetId) {
        List<ExpenseDTOBIT0019> expenses = budgetService.getAllExpenses(budgetId);
        return ResponseEntity.ok(expenses);
    }
}

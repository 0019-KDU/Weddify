package com.chiradev.weddify.service;

import com.chiradev.weddify.dto.BudgetDTOBIT0019;
import com.chiradev.weddify.dto.ExpenseDTOBIT0019;
import com.chiradev.weddify.entity.BudgetDBIT0019;
import com.chiradev.weddify.entity.ExpenseDBIT0019;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.BudgetMapperDBIT0019;
import com.chiradev.weddify.repository.BudgetRepositoryDBIT0019;
import com.chiradev.weddify.repository.ExpenseRepositoryDBIT0019;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetServiceDBIT0019 {

    private final BudgetRepositoryDBIT0019 budgetRepository;
    private final ExpenseRepositoryDBIT0019 expenseRepository;

    public BudgetDTOBIT0019 setBudget(BudgetDTOBIT0019 budgetDTO) {
        log.info("Setting a new budget with name: {}", budgetDTO.getBudgetName());

        if (budgetRepository.existsByBudgetName(budgetDTO.getBudgetName())) {
            throw new IllegalArgumentException("Budget with name '" + budgetDTO.getBudgetName() + "' already exists.");
        }

        BudgetDBIT0019 budgetEntity = BudgetMapperDBIT0019.mapToBudgetEntity(budgetDTO);
        BudgetDBIT0019 savedBudget = budgetRepository.save(budgetEntity);

        return BudgetMapperDBIT0019.mapToBudgetDto(savedBudget);
    }

    @Transactional
    public ExpenseDTOBIT0019 addExpense(Long budgetId, ExpenseDTOBIT0019 expenseDTO) {
        log.info("Adding expense '{}' to budget ID: {}", expenseDTO.getDescription(), budgetId);

        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        ExpenseDBIT0019 expenseEntity = BudgetMapperDBIT0019.mapToExpenseEntity(expenseDTO, budget);
        ExpenseDBIT0019 savedExpense = expenseRepository.save(expenseEntity);

        return BudgetMapperDBIT0019.mapToExpenseDto(savedExpense);
    }

    @Transactional(readOnly = true)
    public BudgetDTOBIT0019 getBudgetSummary(Long budgetId) {
        log.info("Fetching budget summary for budget ID: {}", budgetId);

        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        BudgetDTOBIT0019 budgetDTO = BudgetMapperDBIT0019.mapToBudgetDto(budget);

        // Calculate total expenses
        Double totalExpenses = budget.getExpenses().stream()
                .mapToDouble(ExpenseDBIT0019::getAmount)
                .sum();

        log.info("Total expenses for budget ID {}: {}", budgetId, totalExpenses);
        log.info("Total budget: {}", budget.getTotalBudget());

        // Optionally, you can add totalExpenses to the DTO or calculate it on the client side

        return budgetDTO;
    }

    @Transactional(readOnly = true)
    public ExpenseDTOBIT0019 getExpenseDetails(Long expenseId) {
        log.info("Fetching details for expense ID: {}", expenseId);

        ExpenseDBIT0019 expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

        return BudgetMapperDBIT0019.mapToExpenseDto(expense);
    }

    @Transactional(readOnly = true)
    public List<ExpenseDTOBIT0019> getAllExpenses(Long budgetId) {
        log.info("Fetching all expenses for budget ID: {}", budgetId);

        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        return budget.getExpenses().stream()
                .map(BudgetMapperDBIT0019::mapToExpenseDto)
                .collect(Collectors.toList());
    }
}

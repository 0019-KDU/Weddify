package com.chiradev.weddify.mapper;

import com.chiradev.weddify.dto.BudgetDTOBIT0019;
import com.chiradev.weddify.dto.ExpenseDTOBIT0019;
import com.chiradev.weddify.entity.BudgetDBIT0019;
import com.chiradev.weddify.entity.ExpenseDBIT0019;

import java.util.Set;
import java.util.stream.Collectors;

public class BudgetMapperDBIT0019 {
    public static BudgetDTOBIT0019 mapToBudgetDto(BudgetDBIT0019 budget) {
        Set<ExpenseDTOBIT0019> expenseDTOs = budget.getExpenses() != null ? budget.getExpenses().stream()
                .map(BudgetMapperDBIT0019::mapToExpenseDto)
                .collect(Collectors.toSet()) : null;

        return new BudgetDTOBIT0019(
                budget.getBudgetId(),
                budget.getBudgetName(),
                budget.getTotalBudget(),
                expenseDTOs
        );
    }

    /**
     * Maps Budget DTO to Budget Entity
     *
     * @param budgetDTO Budget DTO
     * @return Budget entity
     */
    public static BudgetDBIT0019 mapToBudgetEntity(BudgetDTOBIT0019 budgetDTO) {
        BudgetDBIT0019 budget = new BudgetDBIT0019();
        budget.setBudgetId(budgetDTO.getBudgetId());
        budget.setBudgetName(budgetDTO.getBudgetName());
        budget.setTotalBudget(budgetDTO.getTotalBudget());
        // Expenses are managed separately to maintain integrity
        return budget;
    }

    /**
     * Maps Expense Entity to Expense DTO
     *
     * @param expense Expense entity
     * @return Expense DTO
     */
    public static ExpenseDTOBIT0019 mapToExpenseDto(ExpenseDBIT0019 expense) {
        return new ExpenseDTOBIT0019(
                expense.getExpenseId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getBudget() != null ? expense.getBudget().getBudgetId() : null,
                expense.getVendorId()
        );
    }

    /**
     * Maps Expense DTO to Expense Entity
     *
     * @param expenseDTO Expense DTO
     * @param budget     Associated Budget entity
     * @return Expense entity
     */
    public static ExpenseDBIT0019 mapToExpenseEntity(ExpenseDTOBIT0019 expenseDTO, BudgetDBIT0019 budget) {
        ExpenseDBIT0019 expense = new ExpenseDBIT0019();
        expense.setExpenseId(expenseDTO.getExpenseId());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setBudget(budget);
        expense.setVendorId(expenseDTO.getVendorId());
        return expense;
    }
}

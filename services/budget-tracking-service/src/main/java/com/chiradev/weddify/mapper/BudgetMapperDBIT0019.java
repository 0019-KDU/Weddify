package com.chiradev.weddify.mapper;

import com.chiradev.weddify.client.VendorClientDBIT0019;
import com.chiradev.weddify.dto.BudgetDTOBIT0019;
import com.chiradev.weddify.dto.ExpenseDTOBIT0019;
import com.chiradev.weddify.dto.VendorDTOBIT0019;
import com.chiradev.weddify.entity.BudgetDBIT0019;
import com.chiradev.weddify.entity.ExpenseDBIT0019;
import com.chiradev.weddify.entity.VendorDBIT0019;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BudgetMapperDBIT0019 {


    // Map Budget Entity to DTO
    public BudgetDTOBIT0019 mapToBudgetDto(BudgetDBIT0019 budget) {
        Set<ExpenseDTOBIT0019> expenseDTOs = budget.getExpenses().stream()
                .map(this::mapToExpenseDto)
                .collect(Collectors.toSet());

        BudgetDTOBIT0019 budgetDTO = new BudgetDTOBIT0019();
        budgetDTO.setBudgetId(budget.getBudgetId());
        budgetDTO.setBudgetName(budget.getBudgetName());
        budgetDTO.setTotalBudget(budget.getTotalBudget());
        budgetDTO.setExpenses(expenseDTOs);
        // Optionally, you can calculate and set remainingBudget here if needed.

        return budgetDTO;
    }

    // Map Budget DTO to Entity
    public BudgetDBIT0019 mapToBudgetEntity(BudgetDTOBIT0019 budgetDTO) {
        BudgetDBIT0019 budget = new BudgetDBIT0019();
        budget.setBudgetId(budgetDTO.getBudgetId());
        budget.setBudgetName(budgetDTO.getBudgetName());
        budget.setTotalBudget(budgetDTO.getTotalBudget());
        // Expenses are managed separately in the service layer.
        return budget;
    }

    // Map Expense Entity to DTO
    public ExpenseDTOBIT0019 mapToExpenseDto(ExpenseDBIT0019 expense) {
        ExpenseDTOBIT0019 expenseDTO = new ExpenseDTOBIT0019();
        expenseDTO.setExpenseId(expense.getExpenseId());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setExpenseDate(expense.getExpenseDate());
        expenseDTO.setBudgetId(expense.getBudget() != null ? expense.getBudget().getBudgetId() : null);
        expenseDTO.setVendorId(expense.getVendorId()); // Vendor ID is now a simple Long field
        expenseDTO.setEventId(null); // Set this if linking to an event is required.
        return expenseDTO;
    }

    // Map Expense DTO to Entity
    public ExpenseDBIT0019 mapToExpenseEntity(ExpenseDTOBIT0019 expenseDTO, BudgetDBIT0019 budget) {
        ExpenseDBIT0019 expense = new ExpenseDBIT0019();
        expense.setExpenseId(expenseDTO.getExpenseId());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setBudget(budget);
        expense.setVendorId(expenseDTO.getVendorId()); // Set vendorId directly as a simple field
        return expense;
    }


}

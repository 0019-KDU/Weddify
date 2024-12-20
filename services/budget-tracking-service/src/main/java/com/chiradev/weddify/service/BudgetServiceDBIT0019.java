package com.chiradev.weddify.service;

import com.chiradev.weddify.client.VendorClientDBIT0019;
import com.chiradev.weddify.dto.BudgetDTOBIT0019;
import com.chiradev.weddify.dto.ExpenseDTOBIT0019;
import com.chiradev.weddify.dto.VendorDTOBIT0019;
import com.chiradev.weddify.entity.BudgetDBIT0019;
import com.chiradev.weddify.entity.ExpenseDBIT0019;
import com.chiradev.weddify.entity.VendorDBIT0019;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.BudgetMapperDBIT0019;
import com.chiradev.weddify.repository.BudgetRepositoryDBIT0019;
import com.chiradev.weddify.repository.ExpenseRepositoryDBIT0019;
import feign.FeignException;
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
    private final VendorClientDBIT0019 vendorClient; // Injected directly
    private final BudgetMapperDBIT0019 budgetMapper;

    public BudgetDTOBIT0019 setBudget(BudgetDTOBIT0019 budgetDTO) {
        log.info("Setting a new budget with name: {}", budgetDTO.getBudgetName());

        if (budgetRepository.existsByBudgetName(budgetDTO.getBudgetName())) {
            throw new IllegalArgumentException("Budget with name '" + budgetDTO.getBudgetName() + "' already exists.");
        }

        BudgetDBIT0019 budgetEntity = budgetMapper.mapToBudgetEntity(budgetDTO);
        BudgetDBIT0019 savedBudget = budgetRepository.save(budgetEntity);

        return budgetMapper.mapToBudgetDto(savedBudget);
    }
    @Transactional
    public ExpenseDTOBIT0019 addExpense(Long budgetId, ExpenseDTOBIT0019 expenseDTO) {
        log.info("Adding expense '{}' to budget ID: {}", expenseDTO.getDescription(), budgetId);

        // Validate Budget
        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        // Validate Vendor
        if (expenseDTO.getVendorId() != null) {
            try {
                vendorClient.checkVendorAvailability(expenseDTO.getVendorId());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Vendor not found with ID: " + expenseDTO.getVendorId());
            } catch (FeignException e) {
                throw new IllegalStateException("Vendor service is unavailable.");
            }
        }

        // Map DTO to Entity
        ExpenseDBIT0019 expenseEntity = budgetMapper.mapToExpenseEntity(expenseDTO, budget);

        // Set Vendor ID
        expenseEntity.setVendorId(expenseDTO.getVendorId());

        // Save Expense
        ExpenseDBIT0019 savedExpense = expenseRepository.save(expenseEntity);

        return budgetMapper.mapToExpenseDto(savedExpense);
    }



    private VendorDBIT0019 mapVendorDtoToEntity(VendorDTOBIT0019 vendorDTO) {
        VendorDBIT0019 vendor = new VendorDBIT0019();
        vendor.setId(vendorDTO.getId());
        vendor.setName(vendorDTO.getName());
        vendor.setVendorType(vendorDTO.getVendorType());
        vendor.setNotes(vendorDTO.getNotes());
        vendor.setContactDetails(vendorDTO.getContactDetails());
        vendor.setPriceRange(vendorDTO.getPriceRange());
        vendor.setAvailable(vendorDTO.getAvailable());
        return vendor;
    }


    @Transactional(readOnly = true)
    public BudgetDTOBIT0019 getBudgetSummary(Long budgetId) {
        // Fetch the budget from the repository
        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        // Calculate total expenses
        double totalExpenses = budget.getExpenses().stream()
                .mapToDouble(ExpenseDBIT0019::getAmount)
                .sum();

        // Calculate the remaining budget
        double remainingBudget = budget.getTotalBudget() - totalExpenses;

        // Map the Budget entity to DTO
        BudgetDTOBIT0019 budgetDTO = budgetMapper.mapToBudgetDto(budget);
        budgetDTO.setRemainingBudget(remainingBudget);  // Set the calculated remaining budget

        return budgetDTO;
    }


    @Transactional(readOnly = true)
    public ExpenseDTOBIT0019 getExpenseDetails(Long expenseId) {
        log.info("Fetching details for expense ID: {}", expenseId);

        ExpenseDBIT0019 expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

        return budgetMapper.mapToExpenseDto(expense);
    }



    @Transactional(readOnly = true)
    public List<ExpenseDTOBIT0019> getAllExpenses(Long budgetId) {
        log.info("Fetching all expenses for budget ID: {}", budgetId);

        BudgetDBIT0019 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        return budget.getExpenses().stream()
                .map(budgetMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }
}

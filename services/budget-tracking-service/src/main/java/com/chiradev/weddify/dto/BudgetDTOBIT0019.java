package com.chiradev.weddify.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTOBIT0019 {
    private Long budgetId;

    @NotBlank(message = "Budget name is mandatory")
    @Size(max = 100, message = "Budget name must be less than 100 characters")
    private String budgetName;

    @NotNull(message = "Total budget amount is mandatory")
    @Positive(message = "Total budget must be a positive number")
    private Double totalBudget;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExpenseDTOBIT0019> expenses;

    @Transient
    private Double remainingBudget;
}

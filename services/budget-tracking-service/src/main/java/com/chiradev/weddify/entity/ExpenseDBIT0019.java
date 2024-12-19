package com.chiradev.weddify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class ExpenseDBIT0019 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", updatable = false, nullable = false)
    private Long expenseId;

    @NotBlank(message = "Expense description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Expense amount is mandatory")
    @Positive(message = "Expense amount must be a positive number")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull(message = "Expense date is mandatory")
    @Column(name = "expense_date", nullable = false)
    private LocalDateTime expenseDate;

    // Many-to-One relationship with Budget
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id", nullable = false)
    private BudgetDBIT0019 budget;

    // Optional: Reference to Vendor
    @Column(name = "vendor_id")
    private Long vendorId;
}

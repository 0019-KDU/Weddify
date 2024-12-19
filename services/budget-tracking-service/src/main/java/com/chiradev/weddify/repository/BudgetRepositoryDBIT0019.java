package com.chiradev.weddify.repository;

import com.chiradev.weddify.entity.BudgetDBIT0019;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepositoryDBIT0019 extends JpaRepository<BudgetDBIT0019,Long> {
    boolean existsByBudgetName(String budgetName);
}

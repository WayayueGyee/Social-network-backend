package com.payroll.spring_rest_tutorial.repository;

import com.payroll.spring_rest_tutorial.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

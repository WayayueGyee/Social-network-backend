package com.payroll.spring_rest_tutorial.model;

import com.payroll.spring_rest_tutorial.controller.EmployeeController;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public @NotNull EntityModel<Employee> toModel(@NotNull Employee employee) {
        return EntityModel.of(
                employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employee")
        );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Employee>> toCollectionModel(Iterable<? extends Employee> employees) {
        List<EntityModel<Employee>> employeeList = new ArrayList<>();

        for (Employee employee : employees) {
            employeeList.add(toModel(employee));
        }

        return CollectionModel.of(
                employeeList,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel()
        );
    }
}

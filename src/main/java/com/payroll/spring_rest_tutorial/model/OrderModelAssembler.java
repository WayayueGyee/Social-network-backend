package com.payroll.spring_rest_tutorial.model;

import com.payroll.spring_rest_tutorial.controller.OrderController;
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
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public @NotNull EntityModel<Order> toModel(@NotNull Order order) {
        EntityModel<Order> orderModel = EntityModel.of(
                order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("order")
        );

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Order>> toCollectionModel(@NotNull Iterable<? extends Order> orders) {
        List<EntityModel<Order>> orderList = new ArrayList<>();

        for (Order order : orders) {
            orderList.add(toModel(order));
        }

        return CollectionModel.of(
                orderList,
                linkTo(methodOn(OrderController.class).all()).withSelfRel()
        );
    }
}

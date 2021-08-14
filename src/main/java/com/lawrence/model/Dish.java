package com.lawrence.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Dish extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double employeePrice;
    private double guestPrice;
    @ManyToMany
    @NotNull
    private Set<Restaurant> restaurants;

    @Override
    public String toString() {
        return "" + id + this.getName();
    }
}

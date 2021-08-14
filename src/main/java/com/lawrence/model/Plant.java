package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Plant extends PointOfService
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToMany(
            cascade = CascadeType.ALL,mappedBy = "plant"
    )
    private List<Restaurant> restaurants=new ArrayList<>();

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
@Override
    public String toString()
    {
      return this.getName();
    }


}

package com.lawrence.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class OpeningSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
@OneToMany(cascade = CascadeType.ALL)
    private Set<OpeningDay> openingDayS;

}

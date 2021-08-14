package com.lawrence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class MenuSection {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "section")
    private List<MenuItem> items;
    
    @OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
    private MenuSectionTemplate template;

}

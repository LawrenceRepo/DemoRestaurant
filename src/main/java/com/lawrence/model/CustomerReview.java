package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CustomerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long					id;

        private String headline;
        private String comment;
        private double rating;
        private Date created;
        @OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.@NotNull
        private UserEntity userEntity;
        @OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.@NotNull
        private Product product;
}

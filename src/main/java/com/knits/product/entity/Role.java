package com.knits.product.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}

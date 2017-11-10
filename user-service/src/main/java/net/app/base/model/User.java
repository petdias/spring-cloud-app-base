package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "usuario")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "data_nasc")
    private Date birthDate;

    @NotNull
    @Column(name = "id_time")
    private Integer team;

}

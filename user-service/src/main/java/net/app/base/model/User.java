package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "usuario")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_nasc")
    private Date birthDate;

    @Column(name = "id_time")
    private Integer team;

}

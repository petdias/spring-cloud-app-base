package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "user")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @SequenceGenerator(name = "sq_user", sequenceName = "sq_user")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "e_mail")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @Column(name = "id_team")
    private Integer team;
}

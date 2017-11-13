package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "client")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq")
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

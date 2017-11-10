package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "campanha")
public final class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String name;

    @NotNull
    @Column(name = "data_inicio")
    private Date startDate;

    @NotNull
    @Column(name = "data_fim")
    private Date endDate;

    @NotNull
    @Column(name = "id_time")
    private Integer team;

}

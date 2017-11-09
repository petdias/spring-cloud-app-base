package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "campanha")
public final class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_inicio")
    private Date startDate;

    @Column(name = "data_fim")
    private Date endDate;

    @Column(name = "id_time")
    private Integer team;

}

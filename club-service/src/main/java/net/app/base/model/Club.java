package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "team")
public final class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_team")
    @SequenceGenerator(name = "sq_team", sequenceName = "sq_team")
    private Integer id;

    private String name;
}

package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "campaign")
public final class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_campaign")
    @SequenceGenerator(name = "sq_campaign", sequenceName = "sq_campaign")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Column(name = "team_id")
    private Integer team;

}

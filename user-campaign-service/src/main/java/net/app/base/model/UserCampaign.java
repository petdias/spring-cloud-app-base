package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "user_campaign")
public final class UserCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user_campaign")
    @SequenceGenerator(name = "sq_user_campaign", sequenceName = "sq_user_campaign")
    private Integer id;

    @Column(name = "id_user")
    private Integer userId;

    @Column(name = "id_campaign")
    private Integer campaignId;
}

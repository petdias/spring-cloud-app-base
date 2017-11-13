package net.app.base.projection;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public final class User {

    private Integer id;

    private String name;

    private String email;

    private Date birthDate;

    private Integer team;

    private List<Campaign> campaigns;

}

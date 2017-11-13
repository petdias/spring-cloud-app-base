package net.app.base.projection;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Campaign {

    public Integer id;

    public String name;

    private Date startDate;

    private Date endDate;

    private Integer team;

    private Boolean selected;
}

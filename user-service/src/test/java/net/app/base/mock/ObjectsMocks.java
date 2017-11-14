package net.app.base.mock;

import net.app.base.model.User;

public class ObjectsMocks {

    public static User getUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("joao@gmail.com");
        user.setName("João");
        user.setTeam(1);

        return user;
    }
}

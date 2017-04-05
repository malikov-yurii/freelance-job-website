package com.malikov.freelance.web.user;

import com.malikov.freelance.model.User;
import com.malikov.freelance.service.UserService;
import com.malikov.freelance.to.BaseUserTo;
import com.malikov.freelance.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUserController {

    @Autowired
    UserService userService;

    public List<BaseUserTo> getAllAdmins() {
        return userService.getAllAdmins().stream().map(UserUtil::asTo).collect(Collectors.toList());
    }

    public void blockUnblock(int userId, boolean isBlocked) {
        User user = userService.get(userId);
        user.setBlocked(isBlocked);
        userService.save(user);
    }

    public void delete(int userId) {
        userService.delete(userId);
    }

    public void create(User user) {
        userService.save(user);
    }

    public void update(BaseUserTo baseUserTo) {
        User user = userService.get(baseUserTo.getId());
        UserUtil.updateFromTo(user, baseUserTo);
        userService.save(user);
    }

}

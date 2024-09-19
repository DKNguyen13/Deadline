package vn.hcmute.services.impl;

import vn.hcmute.dao.IUserDao;
import vn.hcmute.dao.impl.UserDaoImpl;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;

public class UserService implements IUserService {
    //Lấy toàn bộ hàm của tầng dao
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel Login(String username, String password) {
        UserModel user = this.FindByUsername(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public UserModel FindByUsername(String username) {
        return userDao.FindByUsername(username);
    }
}

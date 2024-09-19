package vn.hcmute.services;

import vn.hcmute.models.UserModel;

public interface IUserService {
    UserModel Login(String username, String password);
    UserModel FindByUsername(String username);
}

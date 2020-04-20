package utils;

import model.User;
import model.entities.AuthenticationInfo;
import model.entities.UserProfileInfo;
import utils.mappers.UserMapper;


public class UserMapperImpl implements UserMapper {
    @Override
    public User authenticationInfoToUser(AuthenticationInfo authInfo) {
        User user = new User();
        user.setNickname(authInfo.getNickname());
        user.setEmail(authInfo.getEmail());
        user.setPassword(authInfo.getPassword());
        return user;
    }

    @Override
    public AuthenticationInfo userToAuthenticationInfo(User user) {
        AuthenticationInfo auth = new AuthenticationInfo();
        auth.setNickname(user.getNickname());
        auth.setEmail(user.getEmail());
        auth.setPassword(user.getPassword());
        return auth;
    }

    @Override
    public User userProfileInfoToUser(UserProfileInfo userProfile) {
        return null;
    }

    @Override
    public UserProfileInfo userToUserProfileInfo(User user) {
        return null;
    }
}

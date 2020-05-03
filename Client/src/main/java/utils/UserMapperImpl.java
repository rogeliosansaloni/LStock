package utils;

import model.entities.AuthenticationInfo;
import model.entities.User;
import model.entities.UserProfileInfo;
import utils.mappers.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public User authenticationInfoToUser(AuthenticationInfo authInfo) {
        User user = new User();
        user.setUserId(authInfo.getId());
        user.setNickname(authInfo.getNickname());
        user.setEmail(authInfo.getEmail());
        user.setPassword(authInfo.getPassword());
        user.setTotalBalance(authInfo.getTotal_balance());
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
        User user = new User();
        user.setUserId(userProfile.getUserId());
        user.setNickname(userProfile.getNickname());
        user.setEmail(userProfile.getEmail());
        user.setDescription(userProfile.getDescription());
        user.setTotalBalance(userProfile.getTotalBalance());
        return user;
    }

    @Override
    public UserProfileInfo userToUserProfileInfo(User user) {
        return null;
    }
}

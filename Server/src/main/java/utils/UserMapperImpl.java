package utils;

import model.entities.AuthenticationInfo;
import model.entities.User;
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
        auth.setId(user.getUserId());
        auth.setNickname(user.getNickname());
        auth.setEmail(user.getEmail());
        auth.setPassword(user.getPassword());
        auth.setTotal_balance(user.getTotalBalance());
        return auth;
    }

    @Override
    public User userProfileInfoToUser(UserProfileInfo userInformation) {
        User user = new User();
        user.setUserId(userInformation.getUserId());
        user.setNickname(userInformation.getNickname());
        user.setEmail(userInformation.getEmail());
        user.setDescription(userInformation.getDescription());
        user.setTotalBalance(userInformation.getTotalBalance());
        return user;
    }

    @Override
    public UserProfileInfo userToUserProfileInfo(User user) {
        UserProfileInfo userProfileInfo = new UserProfileInfo();
        userProfileInfo.setNickname(user.getNickname());
        userProfileInfo.setEmail(user.getEmail());
        userProfileInfo.setDescription(user.getDescription());
        userProfileInfo.setTotalBalance(user.getTotalBalance());
        return userProfileInfo;
    }
}

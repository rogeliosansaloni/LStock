package utils;

import model.entities.AuthenticationInfo;
import model.entities.User;
import model.entities.UserProfileInfo;
import utils.mappers.UserMapper;

/**
 * Implementation of UserMapper
 */
public class UserMapperImpl implements UserMapper {
    /**
     * Converts AuthenticationInfo DTO to User POJO
     *
     * @param authInfo authentication information
     * @return User
     */
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

    /**
     * Converts User POJO to AuthenticationInfo DTO
     *
     * @param user User
     * @return AuthenticationInfo object
     */
    @Override
    public AuthenticationInfo userToAuthenticationInfo(User user) {
        AuthenticationInfo auth = new AuthenticationInfo();
        auth.setNickname(user.getNickname());
        auth.setEmail(user.getEmail());
        auth.setPassword(user.getPassword());
        return auth;
    }

    /**
     * Converts UserProfieInfo DTO to User POJO
     *
     * @param userProfile UserProfile DTO
     * @return User
     */
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
}

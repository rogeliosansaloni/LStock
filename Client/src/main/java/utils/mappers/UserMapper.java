package utils.mappers;

import model.entities.AuthenticationInfo;
import model.entities.User;
import model.entities.UserProfileInfo;

/**
 * Mapper interface for User
 */
public interface UserMapper {
    User authenticationInfoToUser(AuthenticationInfo authInfo);

    AuthenticationInfo userToAuthenticationInfo(User user);

    User userProfileInfoToUser(UserProfileInfo userProfile);
}

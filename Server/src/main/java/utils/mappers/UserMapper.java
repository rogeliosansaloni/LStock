package utils.mappers;

import model.entities.AuthenticationInfo;
import model.entities.User;
import model.entities.UserProfileInfo;

/**
 *  User Mapper method interface
 */
public interface UserMapper {
    /**
     * Converts retrieved information into a User
     * @param authInfo Authentication data
     * @return a User object containing the information retrieved.
     */
    User authenticationInfoToUser(AuthenticationInfo authInfo);

    /**
     * Converts retrieved information into an AuthenticationInfo
     * @param user User data
     * @return a AuthenticationInfo object containing the information retrieved.
     */
    AuthenticationInfo userToAuthenticationInfo(User user);

    /**
     * Converts retrieved information into a User
     * @param userProfile User Profile data
     * @return a User object containing the information retrieved.
     */
    User userProfileInfoToUser(UserProfileInfo userProfile);

    /**
     * Converts retrieved information into a UserProfileInfo
     * @param user User data
     * @return a UserProfileInfo object containing the information retrieved.
     */
    UserProfileInfo userToUserProfileInfo(User user);
}

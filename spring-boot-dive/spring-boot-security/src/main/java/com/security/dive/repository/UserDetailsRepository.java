package com.security.dive.repository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理 {@link org.springframework.security.provisioning.UserDetailsManager} 的所有功能
 */
public class UserDetailsRepository {

    /**
     * 保存{@link UserDetails}用户信息
     */
    private Map<String, UserDetails> users = new HashMap<>();

    /**
     * 创建
     *
     * @param user {@link UserDetails}
     */
    public void createUser(UserDetails user) {

        users.putIfAbsent(user.getUsername(), user);
    }

    public void updateUser(UserDetails user) {
        users.put(user.getUsername(), user);

    }

    /**
     * Remove the user with the given login name from the system.
     */
    public void deleteUser(String username) {
        users.remove(username);

    }

    /**
     * Modify the current user's password. This should change the user's password in the
     * persistent user repository (datbase, LDAP etc).
     *
     * @param oldPassword current password (for re-authentication if required)
     * @param newPassword the password to change to
     */
    public void changePassword(String oldPassword, String newPassword)  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw  new IllegalStateException(  "Can't change password as no Authentication object found in context "
                    + "for current user.");
        }
        String username = authentication.getName();
        UserDetails user = users.get(username);

        if(user == null) {
            throw  new IllegalStateException("Current user doesn't exist in database.");
        }
        //更新
        users.put(username, user);
    }

    /**
     * Check if a user with the supplied login name exists in the system.
     */
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }

}

package org.tomis.mvc.model.dto;

/**
 * Project: tomis-mvc
 * @since 24.03.2010
 * @author Dan Persa
 */
public class LoginDto implements Dto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

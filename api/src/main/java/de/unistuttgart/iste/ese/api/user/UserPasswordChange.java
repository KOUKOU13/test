package de.unistuttgart.iste.ese.api.user;

import jakarta.validation.constraints.NotNull;

public class UserPasswordChange {
    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    public UserPasswordChange(@NotNull String oldPassword, @NotNull String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

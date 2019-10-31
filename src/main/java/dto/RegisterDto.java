package dto;

import po.User;

/**
 * @author FHJ
 * @date 2019/10/31 22:54
 */
public class RegisterDto extends User {
    // 确认密码
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

package po;

/**
 * @author FHJ
 * @date 2019/10/26 15:58
 * 用户信息
 */
public class User {
    // 用户id
    private String userId;
    // 姓名
    private String name;
    // 手机
    private String phone;
    // 密码
    private String password;
    // 用户权限
    private Integer permissions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }
}

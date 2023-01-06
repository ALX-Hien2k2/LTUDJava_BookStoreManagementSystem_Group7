package constant;

import java.time.LocalDate;

public class Constant_var {
    String default_password;
    LocalDate default_dob;
    public Constant_var() {
        this.default_password = "123456789";
        this.default_dob = LocalDate.of(2000, 1, 1);
    }
    public LocalDate getDefault_dob() {
        return default_dob;
    }
    public void setDefault_dob(LocalDate default_dob) {
        this.default_dob = default_dob;
    }
    public String getDefault_password() {
        return default_password;
    }
    public void setDefault_password(String default_password) {
        this.default_password = default_password;
    }
}

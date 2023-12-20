package jp.co.sss.crud.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginForm {
    /** 社員ID */
    @Max(value = 99999)
    @NotNull
    private Integer empId;

    /** パスワード */
    @NotBlank
    private String empPass;

    public LoginForm() {
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }
}
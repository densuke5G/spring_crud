package jp.co.sss.crud.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.co.sss.crud.entity.DepartmentEntity;

public class EmployeeForm {
	/** 性別の初期値(男性) */
	public static final Integer DEFAULT_MAN_GENDER = 1;
	/** 部署IDの初期値(営業部) */
	public static final Integer DEFAULT_EIGYO_DEPT_ID = 1;
	/** 権限の初期値(一般) */
	public static final Integer DEFAULT_NORMAL_AUTHORITY = 1;

	/** 社員ID */
	private Integer empId;

	/** パスワード */
	@NotBlank
	@Size(min = 1, max = 16)
	private String empPass;

	/** 社員名 */
	@NotBlank
	@Size(min = 1, max = 30)
	private String empName;

	/** 性別 */
	private Integer gender;

	/** 住所 */
	@NotBlank
	@Size(min = 1, max = 60)
	private String address;

	/** 生年月日 */
	@NotNull
	private Date birthday;

	/** 権限 */
	private Integer authority;

	/** 部署ID */
	private DepartmentEntity department;
	
	/** 部署情報 */
	private DepartmentForm departmentForm;

	public EmployeeForm() {
		gender = DEFAULT_MAN_GENDER;
		authority=DEFAULT_NORMAL_AUTHORITY;
		departmentForm = new DepartmentForm(DEFAULT_EIGYO_DEPT_ID, "");
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public DepartmentForm getDepartmentForm() {
		return departmentForm;
	}

	public void setDepartmentForm(DepartmentForm departmentForm) {
		this.departmentForm = departmentForm;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}
	

}
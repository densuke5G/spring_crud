package jp.co.sss.crud.bean;

import java.io.Serializable;
import java.util.Date;

import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.form.EmployeeForm;

public class EmployeeBean implements Serializable {
	private Integer empId;

	private String empPass;

	private String empName;

	private Integer gender;

	private String address;

	private Date birthday;

	private Integer authority;

	private Integer deptId;

    /** デフォルトコンストラクタの代わり */
	public EmployeeBean() {
	}
	
	/**
	 * 引数entityの内容で初期化
	 * @param entity コピー元のEntityオブジェクト
	 */
	public EmployeeBean(EmployeeEntity entity) {
		this.empId = entity.getEmpId();
		this.empPass = entity.getEmpPass();
		this.empName = entity.getEmpName();
		this.gender = entity.getGender();
		this.address = entity.getAddress();
		this.birthday = entity.getBirthday();
		this.authority = entity.getAuthority();
		this.deptId = entity.getDepartment().getDeptId();
	}
	
	/**
	 * 引数formの内容で初期化
	 * @param form コピー元のFormオブジェクト
	 */
	public EmployeeBean(EmployeeForm form) {
		this.empId = form.getEmpId();
		this.empPass = form.getEmpPass();
		this.empName = form.getEmpName();
		this.gender = form.getGender();
		this.address = form.getAddress();
		this.birthday = form.getBirthday();
		this.authority = form.getAuthority();
		this.deptId = form.getDepartmentForm().getDeptId();
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
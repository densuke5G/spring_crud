package jp.co.sss.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jp.co.sss.crud.form.DepartmentForm;
import jp.co.sss.crud.form.EmployeeForm;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emp_gen")
	@SequenceGenerator(name = "seq_emp_gen", sequenceName = "seq_emp", allocationSize = 1)
	private Integer empId;

	@Column
	private String empPass;

	@Column
	private String empName;

	@Column
	private Integer gender;

	@Column
	private String address;

	@Column
	private Date birthday;

	@Column
	private Integer authority;

	@ManyToOne
	@JoinColumn(name = "dept_id", referencedColumnName = "deptId")
	private DepartmentEntity department;

	public EmployeeEntity() {
	}

	public EmployeeEntity(EmployeeForm form) {
		this.empId = form.getEmpId();
		this.empPass = form.getEmpPass();
		this.empName = form.getEmpName();
		this.gender = form.getGender();
		this.address = form.getAddress();
		this.birthday = form.getBirthday();
		this.authority = form.getAuthority();
		this.department = new DepartmentEntity(form.getDepartmentForm());
	}

	public EmployeeEntity(Integer empId) {
		this.empId = empId;
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

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	/**
	 * Employeeの内容を、EmployeeFormに変換して返却
	 * @return EmployeeFormに変換したEmployee内容
	 */
	public EmployeeForm toEmployeeForm() {
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmpId(empId);
		employeeForm.setEmpPass(empPass);
		employeeForm.setEmpName(empName);
		employeeForm.setGender(gender);
		employeeForm.setAddress(address);
		employeeForm.setBirthday(birthday);
		employeeForm.setAuthority(authority);
		DepartmentForm departmentForm = new DepartmentForm(department.getDeptId(), department.getDeptName());
		employeeForm.setDepartmentForm(departmentForm);
		return employeeForm;
	}
}
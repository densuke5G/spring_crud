package jp.co.sss.crud.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jp.co.sss.crud.form.DepartmentForm;

@Entity
@Table(name = "department")
public class DepartmentEntity {
	@Id
	private Integer deptId;

	@Column
	private String deptName;

	@OneToMany(mappedBy = "department")
	private List<EmployeeEntity> employeeList;

	public DepartmentEntity() {
	}

	public DepartmentEntity(Integer deptId) {
		this.deptId = deptId;
	}

	public DepartmentEntity(Integer deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public DepartmentEntity(DepartmentForm form) {
		this.deptId = form.getDeptId();
		this.deptName = form.getDeptName();
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<EmployeeEntity> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeEntity> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * Departmentの内容を、DepartmentFormに変換して返却
	 * @return DepartmentFormに変換したDepartment内容
	 */
	public DepartmentForm toDepartmentForm() {
		DepartmentForm departmentForm = new DepartmentForm(deptId, deptName);
		return departmentForm;
	}
}
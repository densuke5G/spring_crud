package jp.co.sss.crud.form;

public class DepartmentForm {
	private Integer deptId;

	private String deptName;

	public DepartmentForm() {
	}

	public DepartmentForm(Integer deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
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

}
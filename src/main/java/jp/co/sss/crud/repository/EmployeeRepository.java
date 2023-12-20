package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.crud.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	EmployeeEntity findByEmpIdAndEmpPass(Integer empId, String empPass);
	
	List<EmployeeEntity> findAllByOrderByEmpIdAsc();
	@Query("SELECT i FROM EmployeeEntity i WHERE i.empName LIKE :empName ORDER BY i.empId ASC")
	List<EmployeeEntity> findAllByNameAscQuery(@Param("empName") String empName);
	@Query("SELECT i FROM EmployeeEntity i WHERE i.department.deptId = :deptId ORDER BY i.empId ASC")
	List<EmployeeEntity> findAllByDeptIdAscQuery(@Param("deptId") Integer deptId);
}

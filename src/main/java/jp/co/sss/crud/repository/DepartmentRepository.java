package jp.co.sss.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer>{	
}

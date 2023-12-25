package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 社員一覧表示
	 * @param model	社員データをリクエストスコープに送る
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list(Model model) {
		model.addAttribute("employees", employeeRepository.findAllByOrderByEmpIdAsc());
		return "list/list";
	}
	
	/**
	 * 社員名検索
	 * @param empName	社員名（index.htmlにて入力）
	 * @param model	リクエストスコープ（絞り込み結果）
	 * @return
	 */
	@RequestMapping(path = "/list/empName")
	public String listSearchName(String empName, Model model) {
		if (empName != null && empName.length() > 0) {
            empName = "%" + empName + "%";
        } else {
            empName = "%";
        }
		
		List<EmployeeEntity> result = employeeRepository.findAllByNameAscQuery(empName);
		if (result.isEmpty() || result == null) {
			model.addAttribute("employees", "Empty");
		} else {
			model.addAttribute("employees", result);
			
		}
		return "list/list";
	}
	
	/**
	 * 社員名検索
	 * @param deptId	部署ID（index.htmlにて入力）
	 * @param model	リクエストスコープ（絞り込み結果）
	 * @return
	 */
	@RequestMapping(path = "/list/deptId")
	public String listSearchdeptId(Integer deptId, Model model) {
		List<EmployeeEntity> result = employeeRepository.findAllByDeptIdAscQuery(deptId);
		if (result.isEmpty() || result == null) {
			model.addAttribute("employees", "Empty");
		} else {
			model.addAttribute("employees", result);
			
		}
		model.addAttribute("selectedDeptId", deptId);
		return "list/list";
	}
}

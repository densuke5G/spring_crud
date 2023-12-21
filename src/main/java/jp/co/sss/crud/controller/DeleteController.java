package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
@SessionAttributes(types = EmployeeForm.class)
public class DeleteController {
	
	@Autowired
	private EmployeeRepository repository;
		
	@RequestMapping(path = "/delete/check")
	public String deleteCheck(Integer empId, Model model) {
		EmployeeEntity entity = repository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean(entity);
		model.addAttribute("employee", bean);
		return "delete/delete_check";
	}
	
	@RequestMapping(path = "/delete/doDelete/{empId}", method = RequestMethod.POST)
	public String doUpdate(@PathVariable Integer empId) {
		repository.deleteById(empId);
		return "redirect:/delete/complete";
	}
	
	@RequestMapping(path = "/delete/complete")
	public String deleteComplete() {
		return "delete/delete_complete";
	}
}

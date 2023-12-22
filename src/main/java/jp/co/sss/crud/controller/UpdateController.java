package jp.co.sss.crud.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class UpdateController {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private HttpSession session;

	@RequestMapping(path = "/update/input")
	public String updateInput(Integer empId, EmployeeForm form, Model model) {
		EmployeeEntity entity = repository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean(entity);
		model.addAttribute("employeeForm", bean);
		return "update/update_input";
	}

	@RequestMapping(path = "/update/check", method = RequestMethod.POST)
	public String updateCheck(@Valid @ModelAttribute EmployeeForm form, BindingResult result) {
		if (result.hasErrors()) {
			session.setAttribute("employee", form);
			return "update/update_input";
		}
		form.getDepartmentForm().setDeptId(form.getDeptId());
		session.setAttribute("employee", form);
		return "update/update_check";
	}

	@RequestMapping(path = "/update/doUpdate", method = RequestMethod.POST)
	public String doUpdate(EmployeeForm form) {
		form = (EmployeeForm) session.getAttribute("employee");
		EmployeeEntity entity = new EmployeeEntity(form);
		repository.save(entity);

		return "redirect:/update/complete";
	}

	@RequestMapping(path = "/update/complete")
	public String updateComplete() {
		session.removeAttribute("employee");
		return "update/update_complete";
	}
	
	@RequestMapping(path = "/update/doBack")
	public String doBack(Model model) {
		EmployeeForm form = (EmployeeForm) session.getAttribute("employee");
		model.addAttribute("employeeForm", form);
		session.removeAttribute("employee");
		return "update/update_input";
	}
}

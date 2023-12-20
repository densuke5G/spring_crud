package jp.co.sss.crud.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
@SessionAttributes(types = EmployeeForm.class)
public class RegistrationController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(path = "/regist/input")
	public String registInput(EmployeeForm form, Model model) {
		model.addAttribute("employeeForm", form);
		return "regist/regist_input";
	}
	
	@RequestMapping(path = "/regist/check", method = RequestMethod.POST)
	public String registCheck(@Valid @ModelAttribute EmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "regist/regist_input";
		}
		model.addAttribute("dept", form.getDepartment().getDeptId());
		session.setAttribute("employee", form);
		return "regist/regist_check";
	}
	
	@RequestMapping(path = "/regist/doRegistration", method = RequestMethod.POST)
	public String doRegistration() {
		EmployeeEntity employeeEntity = new EmployeeEntity();
//		employeeEntity = (EmployeeEntity)session.getAttribute("employee");
		BeanUtils.copyProperties(session.getAttribute("employee"), employeeEntity);
		employeeRepository.save(employeeEntity);
		
		return "redirect:/regist/complete";
	}
	
	@RequestMapping(path = "/regist/complete")
	public String registComplete() {
		session.removeAttribute("employee");
		return "regist/regist_complete";
	}
}

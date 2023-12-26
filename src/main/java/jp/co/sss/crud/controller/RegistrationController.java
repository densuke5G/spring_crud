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
public class RegistrationController {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 社員登録初期処理
	 * @param form	入力フォーム
	 * @return	遷移先：（社員情報入力画面）
	 */
	@RequestMapping(path = "/regist/input")
	public String registInput(EmployeeForm form) {
		return "regist/regist_input";
	}
	
	/**
	 * 社員登録確認処理
	 * @param form	入力フォーム
	 * @param result	バリデーション結果を格納する
	 * @return	遷移先：（社員情報確認画面）
	 */
	@RequestMapping(path = "/regist/check", method = RequestMethod.POST)
	public String registCheck(@Valid @ModelAttribute EmployeeForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "regist/regist_input";
		}
		EmployeeBean bean = new EmployeeBean(form);
		session.setAttribute("employee", bean);
		return "regist/regist_check";
	}
	
	/**
	 * 社員登録処理
	 * @return	遷移先：（社員登録完了画面）
	 */
	@RequestMapping(path = "/regist/doRegistration", method = RequestMethod.POST)
	public String doRegistration() {
		EmployeeBean bean = (EmployeeBean)session.getAttribute("employee");
		EmployeeEntity entity = new EmployeeEntity(bean);
		repository.save(entity);
		session.removeAttribute("employee");
		
		return "regist/regist_complete";
	}
	
	/**
	 * 入力画面に戻る処理
	 * @param model	セッション情報をフォームに表示させる
	 * @return	遷移先：（社員情報入力画面）
	 */
	@RequestMapping(path = "/regist/doBack")
	public String doBack(Model model) {
		EmployeeBean bean = (EmployeeBean)session.getAttribute("employee");
		EmployeeForm form = new EmployeeForm(bean);
		model.addAttribute("employeeForm", form);
		session.removeAttribute("employee");
		return "regist/regist_input";
	}
}

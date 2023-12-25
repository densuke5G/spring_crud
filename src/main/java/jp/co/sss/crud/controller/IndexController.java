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
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class IndexController {
	/** 全メソッドで参照するので一括して定義 */
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * ログイン画面表示
	 * @param form	入力フォーム
	 * @param model	入力チェック結果を格納
	 * @return 遷移先(ログインHTML)
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(LoginForm form, Model model) {
		model.addAttribute("loginForm", form);
		session.invalidate();
		return "index";
	}
	
	/**
	 * ログイン処理
	 * @param form	入力フォーム
	 * @param result	入力チェック結果を格納
	 * @param session	ユーザーデータを格納
	 * @return
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginForm form,
			BindingResult result, HttpSession session) {
		
		if (result.hasErrors()) {
			return "index";

		}
		EmployeeEntity employeeEntity = employeeRepository.findByEmpIdAndEmpPass(form.getEmpId(), form.getEmpPass());
		if (employeeEntity != null) {
			EmployeeBean employeeBean = new EmployeeBean(employeeEntity);
			session.setAttribute("employees", employeeBean);
			return "redirect:/list";

		} else {
			return "index";

		}
	}
	
	/**
	 * ログアウト処理
	 * @return	遷移先(ログインHTML)
	 */
	@RequestMapping(path = "/logout")
	public String logout() {
		return "redirect:/";
	}

}
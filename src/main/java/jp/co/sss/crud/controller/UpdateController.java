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
	HttpSession session;
	
	/**
	 * 社員変更初期処理
	 * @param empId	変更する社員IDの値
	 * @param model	入力フォームをセットし、表示
	 * @return	遷移先：（社員情報変更入力画面）
	 */
	@RequestMapping(path = "/update/input")
	public String updateInput(Integer empId, Model model) {
		EmployeeEntity entity = repository.getReferenceById(empId);
		EmployeeForm form = new EmployeeForm(entity);
		model.addAttribute("employeeForm", form);
		return "update/update_input";
	}
	
	/**
	 * 社員変更確認処理
	 * @param form	入力フォーム(リクエストスコープ)
	 * @param result	バリデーション結果を格納する
	 * @return	遷移先：（社員情報変更確認画面）
	 */
	@RequestMapping(path = "/update/check", method = RequestMethod.POST)
	public String updateCheck(@Valid @ModelAttribute EmployeeForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "update/update_input";
		}
		EmployeeBean bean = new EmployeeBean(form);
		session.setAttribute("employee", bean);
		return "update/update_check";
	}
	
	/**
	 * 社員変更処理
	 * @return	遷移先：（社員変更完了画面）
	 */
	@RequestMapping(path = "/update/doUpdate", method = RequestMethod.POST)
	public String doUpdate() {
		EmployeeBean userBean = (EmployeeBean) session.getAttribute("employees");
		EmployeeBean bean = (EmployeeBean) session.getAttribute("employee");
		EmployeeEntity entity = new EmployeeEntity(bean);
		repository.save(entity);
		session.removeAttribute("employee");
//		更新対象がログインユーザーと同じなら、ログイン情報も更新
		if (userBean.getEmpId() == bean.getEmpId()) {
			session.setAttribute("employees", bean);
		}
		return "update/update_complete";
	}
	
	/**
	 * 入力画面に戻る処理
	 * @param model	セッション情報をフォームに表示させる
	 * @return	遷移先：（社員情報変更入力画面）
	 */
	@RequestMapping(path = "/update/doBack")
	public String doBack(Model model) {
		EmployeeBean bean = (EmployeeBean) session.getAttribute("employee");
		if (bean != null) {
			EmployeeForm form = new EmployeeForm(bean);
			model.addAttribute("employeeForm", form);
			session.removeAttribute("employee");
		}
		return "update/update_input";
	}
}

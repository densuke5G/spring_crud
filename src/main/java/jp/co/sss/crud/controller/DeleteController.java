package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.EmployeeEntity;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class DeleteController {
	
	@Autowired
	private EmployeeRepository repository;
		
	/**
	 * 社員削除初期処理
	 * @param empId	削除する社員IDの値
	 * @param model	削除する社員の情報をセットし、表示
	 * @return	遷移先：（社員情報削除入力画面）
	 */
	@RequestMapping(path = "/delete/check")
	public String deleteCheck(Integer empId, Model model) {
		EmployeeEntity entity = repository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean(entity);
		model.addAttribute("employee", bean);
		return "delete/delete_check";
	}
	
	/**
	 * 削除完了処理
	 * @param empI 削除する社員ID：HTML(delete_complete)の[削除]ボタンの値
	 * @return 遷移先：（社員削除完了画面）
	 */
	@RequestMapping(path = "/delete/doDelete", method = RequestMethod.POST)
	public String doUpdate(Integer empId) {
		repository.deleteById(empId);
		return "delete/delete_complete";
	}
}

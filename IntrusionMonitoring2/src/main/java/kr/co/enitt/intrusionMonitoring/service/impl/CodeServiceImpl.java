package kr.co.enitt.intrusionMonitoring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.CodeDAO;
import kr.co.enitt.intrusionMonitoring.service.CodeService;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;

@Service("codeService")
public class CodeServiceImpl implements CodeService{
	@Autowired
	private CodeDAO codeDAO;
	
	/**
	  * @Method_Name : getCodeList
	  * @Method_Description : 코드 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<CodeVO> getCodeList(CommonVO vo) throws Exception {
		List<CodeVO> result = new ArrayList<CodeVO>();
		result = codeDAO.getCodeList(vo);
		return result; 
	}
	
	/**
	  * @Method_Name : getCodeDetail
	  * @Method_Description : 코드 상세
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public CodeVO getCodeDetail(CodeVO vo) throws Exception {
		CodeVO result = new CodeVO();
		result = codeDAO.getCodeDetail(vo);
		return result;
	}
	
	/**
	  * @Method_Name : createCode
	  * @Method_Description : 코드 등록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public void createCode(CodeVO vo) throws Exception {
		codeDAO.createCode(vo);
	}
	
	/**
	  * @Method_Name : updateCode
	  * @Method_Description : 코드 수정
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public void updateCode(CodeVO vo) throws Exception {
		codeDAO.updateCode(vo);
	}
	
	/**
	  * @Method_Name : deleteCode
	  * @Method_Description : 코드 삭제
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public void deleteCode(CodeVO vo) throws Exception {
		codeDAO.deleteCode(vo);
	}
}

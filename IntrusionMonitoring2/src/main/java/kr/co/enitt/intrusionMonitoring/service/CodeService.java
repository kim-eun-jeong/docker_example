package kr.co.enitt.intrusionMonitoring.service;

import java.util.List;

import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;

public interface CodeService {
	//코드 목록
	List<CodeVO> getCodeList(CommonVO vo) throws Exception;
	
	//코드 상세
	CodeVO getCodeDetail(CodeVO vo) throws Exception;
	
	//코드 등록
	void createCode(CodeVO vo) throws Exception;
	
	//코드 수정
	void updateCode(CodeVO vo) throws Exception;
	
	//코드 삭제
	void deleteCode(CodeVO vo) throws Exception;
}

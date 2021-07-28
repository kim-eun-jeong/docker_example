package kr.co.enitt.intrusionMonitoring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;

@Mapper
public interface CodeDAO{
	//코드 목록
	public List<CodeVO> getCodeList(CommonVO vo) throws Exception;
	
	//코드 상세
	public CodeVO getCodeDetail(CodeVO vo) throws Exception;
	
	//코드 등록
	public void createCode(CodeVO vo);
	
	//코드 수정
	public void updateCode(CodeVO vo);
	
	//코드 삭제
	public void deleteCode(CodeVO vo);
	 
}

package kr.co.enitt.intrusionMonitoring.vo;

import java.util.Map;

public class CommonVO {
	
	// 로그인
	private String id; 					//아이디
	private String password;			//비밀번호
	private String newPassword;
	private String passwordChangeYn;
		
	// 메뉴
	private String menuId;
	
	// 공통코드 
	private String codeId;
	private String codeName;
	private String parentCode;
	
	// 모니터링
	private String monitoring;
	private String lastTime;
	private String monitoring_eventId;
	
	// 이력
	private String eventId;	
	private String eventTimeStart;
	private String eventTimeEnd;
	private String eventLocationStart; 
	private String eventLocationEnd;
	private String eventCode;	
	private String eventZone;	
	
	// 통계
	private String searchDay;
	private String searchDayFirst;
	private String searchDayLast;
	private String searchDayCode;
	private String searchMonth;
	private String searchMonthCode;
	private String searchYear;
	private String searchYearEnd;
	private String searchYearCode;

	//페이징 처리
	private String method = "goPage"; // -- 호출 메소드명
	private String pageHtml; // -- 페이지 html
	private int startNum; // -- limit 시작번호
	private int pageSize = 10; // -- 페이지당 보여지는 게시글 수
	private int firstPageNo = 1; // -- 첫번째 페이지 번호
	private int prevPageNo; // -- 이전 페이지 번호
	private int startPageNo; // -- 현재 페이지 기준 시작 페이지
	private int pageNo = 1; // -- 현재 페이지
	private int endPageNo; // -- 현재 페이지 기준 마지막 페이지
	private int nextPageNo; // -- 다음 페이지
	private int finalPageNo; // -- 마지막 페이지 번호
	private int pageCount = 10; // -- 보여지는 페이지 수
	private int totalCount= 0; // -- 총 게시글수
	
	// 기본 검색
	private String searchType; //검색타입
	private String searchWord; //검색어
	
	//결과
	private Boolean result; // -- 성공 : true, 실패 : false
	private String content; // -- 알람창 내용
	private Object value; // -- 결과값
	private Map<String, Object> valueMap; // -- 결과값
	
	// 기타
	private String now;
	private String mode;
	

	
	
	public String getSearchDayCode() {
		return searchDayCode;
	}

	public void setSearchDayCode(String searchDayCode) {
		this.searchDayCode = searchDayCode;
	}

	public String getSearchMonthCode() {
		return searchMonthCode;
	}

	public void setSearchMonthCode(String searchMonthCode) {
		this.searchMonthCode = searchMonthCode;
	}

	public String getSearchYearCode() {
		return searchYearCode;
	}

	public void setSearchYearCode(String searchYearCode) {
		this.searchYearCode = searchYearCode;
	}

	public String getSearchDayFirst() {
		return searchDayFirst;
	}

	public void setSearchDayFirst(String searchDayFirst) {
		this.searchDayFirst = searchDayFirst;
	}

	public String getSearchDayLast() {
		return searchDayLast;
	}

	public void setSearchDayLast(String searchDayLast) {
		this.searchDayLast = searchDayLast;
	}

	public String getEventZone() {
		return eventZone;
	}

	public void setEventZone(String eventZone) {
		this.eventZone = eventZone;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getSearchYearEnd() {
		return searchYearEnd;
	}

	public void setSearchYearEnd(String searchYearEnd) {
		this.searchYearEnd = searchYearEnd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPasswordChangeYn() {
		return passwordChangeYn;
	}

	public void setPasswordChangeYn(String passwordChangeYn) {
		this.passwordChangeYn = passwordChangeYn;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getMonitoring_eventId() {
		return monitoring_eventId;
	}

	public void setMonitoring_eventId(String monitoring_eventId) {
		this.monitoring_eventId = monitoring_eventId;
	}

	public String getEventTimeStart() {
		return eventTimeStart;
	}

	public void setEventTimeStart(String eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}

	public String getEventTimeEnd() {
		return eventTimeEnd;
	}

	public void setEventTimeEnd(String eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}

	public String getEventLocationStart() {
		return eventLocationStart;
	}

	public void setEventLocationStart(String eventLocationStart) {
		this.eventLocationStart = eventLocationStart;
	}

	public String getEventLocationEnd() {
		return eventLocationEnd;
	}

	public void setEventLocationEnd(String eventLocationEnd) {
		this.eventLocationEnd = eventLocationEnd;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	public String getSearchYear() {
		return searchYear;
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	} 

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		makePaging();
		pageHtml();
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private void makePaging() {
		// -- 전체 글수가 없을 경우
		if (totalCount == 0) {
			return;
		} 
		
		int finalPage = (totalCount + (pageSize - 1)) / pageSize;
		if (pageNo > finalPage) {
			this.pageNo = finalPage;
		}

		int startPage = ((pageNo - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		if (endPage > finalPage) {
			endPage = finalPage;
		}

		if (startPage == 1) {
			this.prevPageNo = 1;
		} else {
			this.prevPageNo = startPage - 1;
		}

		if (endPage == finalPage) {
			this.nextPageNo = endPage;
		} else {
			this.nextPageNo = endPage + 1;
		}

		this.startPageNo = startPage;
		this.endPageNo = endPage;
		this.finalPageNo = finalPage;
		this.startNum = (pageNo - 1) * pageSize;
	}
	
	private void pageHtml() {
		StringBuilder html = new StringBuilder();

		
		html.append("<span class=\"inner\">");
		html.append("<a class=\"pageFirst\" href=\"javascript:" + method + "(" + firstPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_first.gif\" alt=\"첫페이지\" />" + "</a>");
		html.append("<a class=\"pagePrev\" href=\"javascript:" + method + "(" + prevPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_pre.gif\" alt=\"이전페이지\" />" + "</a>");
		
		for (int i = startPageNo; i <= endPageNo; i++) {
			if (i == pageNo) {
				html.append("<a class=\"pageNow\" href=\"#\">" + i + "</a>");
			} else {
				html.append("<a class=\"pageNone\" href=\"javascript:" + method + "(" + i + ")\">" + i + "</a>");
			}
		}
		
		html.append("<a class=\"pageNex\" href=\"javascript:" + method + "(" + nextPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_next.gif\" alt=\"다음페이지\" />" + "</a>");
		html.append("<a class=\"pageLast\" href=\"javascript:" + method + "(" + finalPageNo + ")\">" + "<img src=\"/resources/css/lib/images/btn_page_last.gif\" alt=\"마지막페이지\" />" + "</a>");
		html.append("</span>");

		this.pageHtml = html.toString();		
		
		
	}
}
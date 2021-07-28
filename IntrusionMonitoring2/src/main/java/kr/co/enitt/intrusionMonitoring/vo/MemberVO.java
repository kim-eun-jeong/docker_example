package kr.co.enitt.intrusionMonitoring.vo;

public class MemberVO {
	private String id; 					//아이디
	private String password;			//비밀번호
	private String name;				//이름
	private String email;				//메일
	private String auth;				//권한
	private String authNm;				//권한명
	private String passwordChangeYn;	//비밀번호 변경 여부
	private String department;			//소속
	private String departmentNm;		//소속명
	private String useYn;				//사용여부
	private String registerDate;		//등록일
	private String registerId;			//등록자
	private String registerIp;			//등록IP
	private String updateDate;			//수정일
	private String updateId;			//수정ID
	private String updateIp;			//수정IP
	private String message; 
	private boolean result; 
	
	public String getAuthNm() {
		return authNm;
	}
	public void setAuthNm(String authNm) {
		this.authNm = authNm;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPasswordChangeYn() {
		return passwordChangeYn;
	}
	public void setPasswordChangeYn(String passwordChangeYn) {
		this.passwordChangeYn = passwordChangeYn;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDepartmentNm() {
		return departmentNm;
	}
	public void setDepartmentNm(String departmentName) {
		this.departmentNm = departmentName;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
package kr.co.enitt.intrusionMonitoring.vo;

public class MonitoringVO {
	private String equipmentId;
	private String equipmentName;
	private String equipmentState;
	private String equipmentNote;
	private String updateDate;
	

	private String location;
	private String lattitude;
	private String longitude;
	private String zoneType;
	private String note;
	
	
	
	public String getZoneType() {
		return zoneType;
	}
	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
	public String getEquipmentNote() {
		return equipmentNote;
	}
	public void setEquipmentNote(String equipmentNote) {
		this.equipmentNote = equipmentNote;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
package kr.co.enitt.intrusionMonitoring.vo;

public class EventStatsVO {
	
	private String EventDate;
	private String eventDateGroup;
	private String eventCode;
	private String eventCodeName;
	private int eventCount;
	private Integer eventCountNull;
	
	private String eventTime;
	private String eventTimeGroup;
	
	
	
	public Integer getEventCountNull() {
		return eventCountNull;
	}
	public void setEventCountNull(Integer eventCountNull) {
		this.eventCountNull = eventCountNull;
	}
	public String getEventDateGroup() {
		return eventDateGroup;
	}
	public void setEventDateGroup(String eventDateGroup) {
		this.eventDateGroup = eventDateGroup;
	}
	public String getEventDate() {
		return EventDate;
	}
	public void setEventDate(String eventDate) {
		EventDate = eventDate;
	}
	public String getEventCodeName() {
		return eventCodeName;
	}
	public void setEventCodeName(String eventCodeName) {
		this.eventCodeName = eventCodeName;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventTimeGroup() {
		return eventTimeGroup;
	}
	public void setEventTimeGroup(String eventTimeGroup) {
		this.eventTimeGroup = eventTimeGroup;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	
	
	
}
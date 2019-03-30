package com.uhealin.flow;

import java.util.Date;

public class Event {

	private String msg;   //属性，用于在Event对象中存储消息。
	private String source ; //属性，用于存储生成Event对象的类的名称。
	private Date date ;//属性，用于存储Event生成的日期。
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}

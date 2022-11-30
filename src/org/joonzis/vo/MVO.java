package org.joonzis.vo;

import java.sql.Date;

public class MVO {
	int m_idx;
	String m_id;
	String m_pw;
	String m_name;
	String m_email;
	String m_self;
	Date m_reg_date;
	
	public MVO() {}
	public MVO(int m_idx, String m_id, String m_pw, String m_name, String m_email, String m_self, Date m_reg_date) {
		this.m_idx = m_idx;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_self = m_self;
		this.m_reg_date = m_reg_date;
	}
	
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_self() {
		return m_self;
	}
	public void setM_self(String m_self) {
		this.m_self = m_self;
	}
	public Date getM_reg_date() {
		return m_reg_date;
	}
	public void setM_reg_date(Date m_reg_date) {
		this.m_reg_date = m_reg_date;
	}
}

package com.br.mf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstitutionClient {
	@JsonProperty("CNTL_NBR_KEY")
	private String cntlNumberKey;
	@JsonProperty("JOB_NBR")
	private String jobNbr;
	@JsonProperty("MONEY_MGR_NO")
	private String MoneyMgrNum;
	@JsonProperty("PIN")
	private String pin;    
	@JsonProperty("CLNT_PIN_REQD")
	private String cltnPinRecord;
	@JsonProperty("EDELIVERY_FLAG")
	private String edeliveryFlag; 
	@JsonProperty("HOUSHOLDING_FLAG")
	private String householdingFlag;  
	@JsonProperty("VOTE_INSTRUCTION")
	private String voteInstruction;
	public String getCntlNumberKey() {
		return cntlNumberKey;
	}
	public void setCntlNumberKey(String cntlNumberKey) {
		this.cntlNumberKey = cntlNumberKey;
	}
	public String getJobNbr() {
		return jobNbr;
	}
	public void setJobNbr(String jobNbr) {
		this.jobNbr = jobNbr;
	}
	public String getMoneyMgrNum() {
		return MoneyMgrNum;
	}
	public void setMoneyMgrNum(String moneyMgrNum) {
		MoneyMgrNum = moneyMgrNum;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCltnPinRecord() {
		return cltnPinRecord;
	}
	public void setCltnPinRecord(String cltnPinRecord) {
		this.cltnPinRecord = cltnPinRecord;
	}
	public String getEdeliveryFlag() {
		return edeliveryFlag;
	}
	public void setEdeliveryFlag(String edeliveryFlag) {
		this.edeliveryFlag = edeliveryFlag;
	}
	public String getHouseholdingFlag() {
		return householdingFlag;
	}
	public void setHouseholdingFlag(String householdingFlag) {
		this.householdingFlag = householdingFlag;
	}
	public String getVOteInstruction() {
		return voteInstruction;
	}
	public void setVOteInstruction(String voteInstruction) {
		this.voteInstruction = voteInstruction;
	} 

}

package com.erpnext.common.param.domain;

public class Currency {
    private String id;

    private String chineseName;

    private String englishName;

    private Byte amountDecNo;

    private Byte exchangeRateDecNo;

    private String unit;

    private Byte status;
    
    private Byte enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Byte getAmountDecNo() {
        return amountDecNo;
    }

    public void setAmountDecNo(Byte amountDecNo) {
        this.amountDecNo = amountDecNo;
    }

    public Byte getExchangeRateDecNo() {
        return exchangeRateDecNo;
    }

    public void setExchangeRateDecNo(Byte exchangeRateDecNo) {
        this.exchangeRateDecNo = exchangeRateDecNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}
}
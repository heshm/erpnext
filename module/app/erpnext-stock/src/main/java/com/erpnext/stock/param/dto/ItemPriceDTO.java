package com.erpnext.stock.param.dto;

import java.math.BigDecimal;

import com.erpnext.common.param.domain.Currency;
import com.erpnext.stock.param.domain.Item;
import com.erpnext.stock.param.domain.Price;

public class ItemPriceDTO {
	
	private String itemId;

    private String priceId;
    
    private String currencyId;

    private BigDecimal standPriceRate;

    private BigDecimal maxPriceRate;

    private BigDecimal minPriceRate;

    private Byte enabled;
    
    private Item item;
    
    private Price price;
    
    private Currency currency;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	
	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public BigDecimal getStandPriceRate() {
		return standPriceRate;
	}

	public void setStandPriceRate(BigDecimal standPriceRate) {
		this.standPriceRate = standPriceRate;
	}

	public BigDecimal getMaxPriceRate() {
		return maxPriceRate;
	}

	public void setMaxPriceRate(BigDecimal maxPriceRate) {
		this.maxPriceRate = maxPriceRate;
	}

	public BigDecimal getMinPriceRate() {
		return minPriceRate;
	}

	public void setMinPriceRate(BigDecimal minPriceRate) {
		this.minPriceRate = minPriceRate;
	}

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}

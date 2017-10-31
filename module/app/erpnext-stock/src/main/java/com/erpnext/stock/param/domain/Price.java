package com.erpnext.stock.param.domain;

public class Price {
    private String id;

    private String name;

    private Byte enabled;

    private String currency;

    private Byte buying;

    private Byte selling;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Byte getBuying() {
        return buying;
    }

    public void setBuying(Byte buying) {
        this.buying = buying;
    }

    public Byte getSelling() {
        return selling;
    }

    public void setSelling(Byte selling) {
        this.selling = selling;
    }
}
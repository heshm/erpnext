package com.erpnext.framework.domain;

public class Sequence {
    private String id;

    private Short resetCycle;

    private String resetCycleUnit;

    private Short length;

    private Integer currentSequenceNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getResetCycle() {
        return resetCycle;
    }

    public void setResetCycle(Short resetCycle) {
        this.resetCycle = resetCycle;
    }

    public String getResetCycleUnit() {
        return resetCycleUnit;
    }

    public void setResetCycleUnit(String resetCycleUnit) {
        this.resetCycleUnit = resetCycleUnit;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public Integer getCurrentSequenceNo() {
        return currentSequenceNo;
    }

    public void setCurrentSequenceNo(Integer currentSequenceNo) {
        this.currentSequenceNo = currentSequenceNo;
    }
}
package com.erpnext.ledger.account.domain;

public class AccountTitle {
    private String accountNo;

    private String name;

    private String shortName;
    /**
     * 科目性质 
     * 0=资产; 1=负债; 2=共同; 3=所有者权益; 
     * 4=损益; 5=表外科目
     */
    private Byte accountCharacter;

    /**
     * @category
     * 发生额方向
     * 0=借方; 1=贷方; 2=借方或贷方;3=收方;
     * 4=付方;5=收方或付方
     */
    private Byte amountDirection;

    /**
     * 余额方向
     * 0=借方; 1=贷方; 2=借方或贷方; 
     * 3=借方和贷方;4=收方
     */
    private Byte balanceDirection;
    /**
     * 科目层级
     * 1=一级;2=二级;3=三级
     */
    private Byte level;

    /**
     * 表内表外标志
     * 0-表内;1-表外
     */
    private Byte tableFlag;

    /**
     * 上级科目
     */
    private String parent;

    private Byte status;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAccountCharacter() {
        return String.valueOf(accountCharacter);
    }

    public void setAccountCharacter(Byte accountCharacter) {
        this.accountCharacter = accountCharacter;
    }

    public String getAmountDirection() {
        return String.valueOf(amountDirection);
    }

    public void setAmountDirection(Byte amountDirection) {
        this.amountDirection = amountDirection;
    }

    public String getBalanceDirection() {
        return String.valueOf(balanceDirection);
    }

    public void setBalanceDirection(Byte balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getTableFlag() {
        return tableFlag;
    }

    public void setTableFlag(Byte tableFlag) {
        this.tableFlag = tableFlag;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
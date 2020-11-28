package com.nkdark.bot.pojo;


/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/11 2:14
 * @Description:
 */


public class UserInfo {

    /**
     * 用户编号，没啥用
     */
    private Long id;
    /**
     * 用户QQ号
     */
    private Long userId;
    /**
     * 用户余额 初始值100.00
     */
    private Double balance;

    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}

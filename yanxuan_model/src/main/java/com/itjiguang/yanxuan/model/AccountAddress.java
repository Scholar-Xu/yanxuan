package com.itjiguang.yanxuan.model;

import java.io.Serializable;

public class AccountAddress  implements Serializable {
    private Long id;

    private Long accountId;

    private String areaProvinceId;

    private String areaCityId;

    private String areaTownId;

    private String detailAddress;

    private String receiver;

    private String phoneNum;

    private String postalCode;

    private String isDefault;

    private String alias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAreaProvinceId() {
        return areaProvinceId;
    }

    public void setAreaProvinceId(String areaProvinceId) {
        this.areaProvinceId = areaProvinceId == null ? null : areaProvinceId.trim();
    }

    public String getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(String areaCityId) {
        this.areaCityId = areaCityId == null ? null : areaCityId.trim();
    }

    public String getAreaTownId() {
        return areaTownId;
    }

    public void setAreaTownId(String areaTownId) {
        this.areaTownId = areaTownId == null ? null : areaTownId.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }
}
package com.itjiguang.yanxuan.model;

import java.io.Serializable;
import java.util.Date;

public class SellerShop  implements Serializable {
    private Long id;

    private String name;

    private Long accountId;

    private String linkmanName;

    private String linkmanPhone;

    private String linkmanEmail;

    private String linkmanQq;

    private String companyName;

    private String companyLicense;

    private String companyOrgcode;

    private String companyCreate;

    private String companyArea;

    private String companyAddress;

    private String companyTel;

    private String companyMajor;

    private String legalName;

    private String legalCardId;

    private String bankAccount;

    private String bankName;

    private String bankBranch;

    private String categoryIds;

    private String brandIds;

    private String status;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName == null ? null : linkmanName.trim();
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail == null ? null : linkmanEmail.trim();
    }

    public String getLinkmanQq() {
        return linkmanQq;
    }

    public void setLinkmanQq(String linkmanQq) {
        this.linkmanQq = linkmanQq == null ? null : linkmanQq.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyLicense() {
        return companyLicense;
    }

    public void setCompanyLicense(String companyLicense) {
        this.companyLicense = companyLicense == null ? null : companyLicense.trim();
    }

    public String getCompanyOrgcode() {
        return companyOrgcode;
    }

    public void setCompanyOrgcode(String companyOrgcode) {
        this.companyOrgcode = companyOrgcode == null ? null : companyOrgcode.trim();
    }

    public String getCompanyCreate() {
        return companyCreate;
    }

    public void setCompanyCreate(String companyCreate) {
        this.companyCreate = companyCreate == null ? null : companyCreate.trim();
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea == null ? null : companyArea.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getCompanyMajor() {
        return companyMajor;
    }

    public void setCompanyMajor(String companyMajor) {
        this.companyMajor = companyMajor == null ? null : companyMajor.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalCardId() {
        return legalCardId;
    }

    public void setLegalCardId(String legalCardId) {
        this.legalCardId = legalCardId == null ? null : legalCardId.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds == null ? null : categoryIds.trim();
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds == null ? null : brandIds.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
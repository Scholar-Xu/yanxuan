package com.itjiguang.yanxuan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerShopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIsNull() {
            addCriterion("linkman_name is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIsNotNull() {
            addCriterion("linkman_name is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameEqualTo(String value) {
            addCriterion("linkman_name =", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotEqualTo(String value) {
            addCriterion("linkman_name <>", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameGreaterThan(String value) {
            addCriterion("linkman_name >", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_name >=", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLessThan(String value) {
            addCriterion("linkman_name <", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLessThanOrEqualTo(String value) {
            addCriterion("linkman_name <=", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLike(String value) {
            addCriterion("linkman_name like", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotLike(String value) {
            addCriterion("linkman_name not like", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIn(List<String> values) {
            addCriterion("linkman_name in", values, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotIn(List<String> values) {
            addCriterion("linkman_name not in", values, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameBetween(String value1, String value2) {
            addCriterion("linkman_name between", value1, value2, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotBetween(String value1, String value2) {
            addCriterion("linkman_name not between", value1, value2, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNull() {
            addCriterion("linkman_phone is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNotNull() {
            addCriterion("linkman_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneEqualTo(String value) {
            addCriterion("linkman_phone =", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotEqualTo(String value) {
            addCriterion("linkman_phone <>", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThan(String value) {
            addCriterion("linkman_phone >", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_phone >=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThan(String value) {
            addCriterion("linkman_phone <", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThanOrEqualTo(String value) {
            addCriterion("linkman_phone <=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLike(String value) {
            addCriterion("linkman_phone like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotLike(String value) {
            addCriterion("linkman_phone not like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIn(List<String> values) {
            addCriterion("linkman_phone in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotIn(List<String> values) {
            addCriterion("linkman_phone not in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneBetween(String value1, String value2) {
            addCriterion("linkman_phone between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotBetween(String value1, String value2) {
            addCriterion("linkman_phone not between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailIsNull() {
            addCriterion("linkman_email is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailIsNotNull() {
            addCriterion("linkman_email is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailEqualTo(String value) {
            addCriterion("linkman_email =", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailNotEqualTo(String value) {
            addCriterion("linkman_email <>", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailGreaterThan(String value) {
            addCriterion("linkman_email >", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_email >=", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailLessThan(String value) {
            addCriterion("linkman_email <", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailLessThanOrEqualTo(String value) {
            addCriterion("linkman_email <=", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailLike(String value) {
            addCriterion("linkman_email like", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailNotLike(String value) {
            addCriterion("linkman_email not like", value, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailIn(List<String> values) {
            addCriterion("linkman_email in", values, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailNotIn(List<String> values) {
            addCriterion("linkman_email not in", values, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailBetween(String value1, String value2) {
            addCriterion("linkman_email between", value1, value2, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmailNotBetween(String value1, String value2) {
            addCriterion("linkman_email not between", value1, value2, "linkmanEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqIsNull() {
            addCriterion("linkman_qq is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqIsNotNull() {
            addCriterion("linkman_qq is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqEqualTo(String value) {
            addCriterion("linkman_qq =", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqNotEqualTo(String value) {
            addCriterion("linkman_qq <>", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqGreaterThan(String value) {
            addCriterion("linkman_qq >", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_qq >=", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqLessThan(String value) {
            addCriterion("linkman_qq <", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqLessThanOrEqualTo(String value) {
            addCriterion("linkman_qq <=", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqLike(String value) {
            addCriterion("linkman_qq like", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqNotLike(String value) {
            addCriterion("linkman_qq not like", value, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqIn(List<String> values) {
            addCriterion("linkman_qq in", values, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqNotIn(List<String> values) {
            addCriterion("linkman_qq not in", values, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqBetween(String value1, String value2) {
            addCriterion("linkman_qq between", value1, value2, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andLinkmanQqNotBetween(String value1, String value2) {
            addCriterion("linkman_qq not between", value1, value2, "linkmanQq");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseIsNull() {
            addCriterion("company_license is null");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseIsNotNull() {
            addCriterion("company_license is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseEqualTo(String value) {
            addCriterion("company_license =", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseNotEqualTo(String value) {
            addCriterion("company_license <>", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseGreaterThan(String value) {
            addCriterion("company_license >", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("company_license >=", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseLessThan(String value) {
            addCriterion("company_license <", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseLessThanOrEqualTo(String value) {
            addCriterion("company_license <=", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseLike(String value) {
            addCriterion("company_license like", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseNotLike(String value) {
            addCriterion("company_license not like", value, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseIn(List<String> values) {
            addCriterion("company_license in", values, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseNotIn(List<String> values) {
            addCriterion("company_license not in", values, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseBetween(String value1, String value2) {
            addCriterion("company_license between", value1, value2, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyLicenseNotBetween(String value1, String value2) {
            addCriterion("company_license not between", value1, value2, "companyLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeIsNull() {
            addCriterion("company_orgcode is null");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeIsNotNull() {
            addCriterion("company_orgcode is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeEqualTo(String value) {
            addCriterion("company_orgcode =", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeNotEqualTo(String value) {
            addCriterion("company_orgcode <>", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeGreaterThan(String value) {
            addCriterion("company_orgcode >", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_orgcode >=", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeLessThan(String value) {
            addCriterion("company_orgcode <", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeLessThanOrEqualTo(String value) {
            addCriterion("company_orgcode <=", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeLike(String value) {
            addCriterion("company_orgcode like", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeNotLike(String value) {
            addCriterion("company_orgcode not like", value, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeIn(List<String> values) {
            addCriterion("company_orgcode in", values, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeNotIn(List<String> values) {
            addCriterion("company_orgcode not in", values, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeBetween(String value1, String value2) {
            addCriterion("company_orgcode between", value1, value2, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyOrgcodeNotBetween(String value1, String value2) {
            addCriterion("company_orgcode not between", value1, value2, "companyOrgcode");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateIsNull() {
            addCriterion("company_create is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateIsNotNull() {
            addCriterion("company_create is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateEqualTo(String value) {
            addCriterion("company_create =", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateNotEqualTo(String value) {
            addCriterion("company_create <>", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateGreaterThan(String value) {
            addCriterion("company_create >", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateGreaterThanOrEqualTo(String value) {
            addCriterion("company_create >=", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateLessThan(String value) {
            addCriterion("company_create <", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateLessThanOrEqualTo(String value) {
            addCriterion("company_create <=", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateLike(String value) {
            addCriterion("company_create like", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateNotLike(String value) {
            addCriterion("company_create not like", value, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateIn(List<String> values) {
            addCriterion("company_create in", values, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateNotIn(List<String> values) {
            addCriterion("company_create not in", values, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateBetween(String value1, String value2) {
            addCriterion("company_create between", value1, value2, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateNotBetween(String value1, String value2) {
            addCriterion("company_create not between", value1, value2, "companyCreate");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaIsNull() {
            addCriterion("company_area is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaIsNotNull() {
            addCriterion("company_area is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaEqualTo(String value) {
            addCriterion("company_area =", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaNotEqualTo(String value) {
            addCriterion("company_area <>", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaGreaterThan(String value) {
            addCriterion("company_area >", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaGreaterThanOrEqualTo(String value) {
            addCriterion("company_area >=", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaLessThan(String value) {
            addCriterion("company_area <", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaLessThanOrEqualTo(String value) {
            addCriterion("company_area <=", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaLike(String value) {
            addCriterion("company_area like", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaNotLike(String value) {
            addCriterion("company_area not like", value, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaIn(List<String> values) {
            addCriterion("company_area in", values, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaNotIn(List<String> values) {
            addCriterion("company_area not in", values, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaBetween(String value1, String value2) {
            addCriterion("company_area between", value1, value2, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAreaNotBetween(String value1, String value2) {
            addCriterion("company_area not between", value1, value2, "companyArea");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIsNull() {
            addCriterion("company_tel is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIsNotNull() {
            addCriterion("company_tel is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTelEqualTo(String value) {
            addCriterion("company_tel =", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotEqualTo(String value) {
            addCriterion("company_tel <>", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelGreaterThan(String value) {
            addCriterion("company_tel >", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelGreaterThanOrEqualTo(String value) {
            addCriterion("company_tel >=", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLessThan(String value) {
            addCriterion("company_tel <", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLessThanOrEqualTo(String value) {
            addCriterion("company_tel <=", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLike(String value) {
            addCriterion("company_tel like", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotLike(String value) {
            addCriterion("company_tel not like", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIn(List<String> values) {
            addCriterion("company_tel in", values, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotIn(List<String> values) {
            addCriterion("company_tel not in", values, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelBetween(String value1, String value2) {
            addCriterion("company_tel between", value1, value2, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotBetween(String value1, String value2) {
            addCriterion("company_tel not between", value1, value2, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorIsNull() {
            addCriterion("company_major is null");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorIsNotNull() {
            addCriterion("company_major is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorEqualTo(String value) {
            addCriterion("company_major =", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorNotEqualTo(String value) {
            addCriterion("company_major <>", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorGreaterThan(String value) {
            addCriterion("company_major >", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorGreaterThanOrEqualTo(String value) {
            addCriterion("company_major >=", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorLessThan(String value) {
            addCriterion("company_major <", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorLessThanOrEqualTo(String value) {
            addCriterion("company_major <=", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorLike(String value) {
            addCriterion("company_major like", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorNotLike(String value) {
            addCriterion("company_major not like", value, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorIn(List<String> values) {
            addCriterion("company_major in", values, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorNotIn(List<String> values) {
            addCriterion("company_major not in", values, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorBetween(String value1, String value2) {
            addCriterion("company_major between", value1, value2, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andCompanyMajorNotBetween(String value1, String value2) {
            addCriterion("company_major not between", value1, value2, "companyMajor");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNull() {
            addCriterion("legal_name is null");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNotNull() {
            addCriterion("legal_name is not null");
            return (Criteria) this;
        }

        public Criteria andLegalNameEqualTo(String value) {
            addCriterion("legal_name =", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotEqualTo(String value) {
            addCriterion("legal_name <>", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThan(String value) {
            addCriterion("legal_name >", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThanOrEqualTo(String value) {
            addCriterion("legal_name >=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThan(String value) {
            addCriterion("legal_name <", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThanOrEqualTo(String value) {
            addCriterion("legal_name <=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLike(String value) {
            addCriterion("legal_name like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotLike(String value) {
            addCriterion("legal_name not like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameIn(List<String> values) {
            addCriterion("legal_name in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotIn(List<String> values) {
            addCriterion("legal_name not in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameBetween(String value1, String value2) {
            addCriterion("legal_name between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotBetween(String value1, String value2) {
            addCriterion("legal_name not between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdIsNull() {
            addCriterion("legal_card_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdIsNotNull() {
            addCriterion("legal_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdEqualTo(String value) {
            addCriterion("legal_card_id =", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdNotEqualTo(String value) {
            addCriterion("legal_card_id <>", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdGreaterThan(String value) {
            addCriterion("legal_card_id >", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_card_id >=", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdLessThan(String value) {
            addCriterion("legal_card_id <", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdLessThanOrEqualTo(String value) {
            addCriterion("legal_card_id <=", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdLike(String value) {
            addCriterion("legal_card_id like", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdNotLike(String value) {
            addCriterion("legal_card_id not like", value, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdIn(List<String> values) {
            addCriterion("legal_card_id in", values, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdNotIn(List<String> values) {
            addCriterion("legal_card_id not in", values, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdBetween(String value1, String value2) {
            addCriterion("legal_card_id between", value1, value2, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andLegalCardIdNotBetween(String value1, String value2) {
            addCriterion("legal_card_id not between", value1, value2, "legalCardId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNull() {
            addCriterion("bank_branch is null");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNotNull() {
            addCriterion("bank_branch is not null");
            return (Criteria) this;
        }

        public Criteria andBankBranchEqualTo(String value) {
            addCriterion("bank_branch =", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotEqualTo(String value) {
            addCriterion("bank_branch <>", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThan(String value) {
            addCriterion("bank_branch >", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThanOrEqualTo(String value) {
            addCriterion("bank_branch >=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThan(String value) {
            addCriterion("bank_branch <", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThanOrEqualTo(String value) {
            addCriterion("bank_branch <=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLike(String value) {
            addCriterion("bank_branch like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotLike(String value) {
            addCriterion("bank_branch not like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchIn(List<String> values) {
            addCriterion("bank_branch in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotIn(List<String> values) {
            addCriterion("bank_branch not in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchBetween(String value1, String value2) {
            addCriterion("bank_branch between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotBetween(String value1, String value2) {
            addCriterion("bank_branch not between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNull() {
            addCriterion("category_ids is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNotNull() {
            addCriterion("category_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsEqualTo(String value) {
            addCriterion("category_ids =", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotEqualTo(String value) {
            addCriterion("category_ids <>", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThan(String value) {
            addCriterion("category_ids >", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThanOrEqualTo(String value) {
            addCriterion("category_ids >=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThan(String value) {
            addCriterion("category_ids <", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThanOrEqualTo(String value) {
            addCriterion("category_ids <=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLike(String value) {
            addCriterion("category_ids like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotLike(String value) {
            addCriterion("category_ids not like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIn(List<String> values) {
            addCriterion("category_ids in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotIn(List<String> values) {
            addCriterion("category_ids not in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsBetween(String value1, String value2) {
            addCriterion("category_ids between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotBetween(String value1, String value2) {
            addCriterion("category_ids not between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNull() {
            addCriterion("brand_ids is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNotNull() {
            addCriterion("brand_ids is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsEqualTo(String value) {
            addCriterion("brand_ids =", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotEqualTo(String value) {
            addCriterion("brand_ids <>", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThan(String value) {
            addCriterion("brand_ids >", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThanOrEqualTo(String value) {
            addCriterion("brand_ids >=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThan(String value) {
            addCriterion("brand_ids <", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThanOrEqualTo(String value) {
            addCriterion("brand_ids <=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLike(String value) {
            addCriterion("brand_ids like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotLike(String value) {
            addCriterion("brand_ids not like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIn(List<String> values) {
            addCriterion("brand_ids in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotIn(List<String> values) {
            addCriterion("brand_ids not in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsBetween(String value1, String value2) {
            addCriterion("brand_ids between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotBetween(String value1, String value2) {
            addCriterion("brand_ids not between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
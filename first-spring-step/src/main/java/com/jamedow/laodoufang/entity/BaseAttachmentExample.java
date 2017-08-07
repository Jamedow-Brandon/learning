package com.jamedow.laodoufang.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseAttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseAttachmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andResourceidIsNull() {
            addCriterion("resourceId is null");
            return (Criteria) this;
        }

        public Criteria andResourceidIsNotNull() {
            addCriterion("resourceId is not null");
            return (Criteria) this;
        }

        public Criteria andResourceidEqualTo(Integer value) {
            addCriterion("resourceId =", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidNotEqualTo(Integer value) {
            addCriterion("resourceId <>", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidGreaterThan(Integer value) {
            addCriterion("resourceId >", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("resourceId >=", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidLessThan(Integer value) {
            addCriterion("resourceId <", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidLessThanOrEqualTo(Integer value) {
            addCriterion("resourceId <=", value, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidIn(List<Integer> values) {
            addCriterion("resourceId in", values, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidNotIn(List<Integer> values) {
            addCriterion("resourceId not in", values, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidBetween(Integer value1, Integer value2) {
            addCriterion("resourceId between", value1, value2, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourceidNotBetween(Integer value1, Integer value2) {
            addCriterion("resourceId not between", value1, value2, "resourceid");
            return (Criteria) this;
        }

        public Criteria andResourcetypeIsNull() {
            addCriterion("resourceType is null");
            return (Criteria) this;
        }

        public Criteria andResourcetypeIsNotNull() {
            addCriterion("resourceType is not null");
            return (Criteria) this;
        }

        public Criteria andResourcetypeEqualTo(String value) {
            addCriterion("resourceType =", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeNotEqualTo(String value) {
            addCriterion("resourceType <>", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeGreaterThan(String value) {
            addCriterion("resourceType >", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeGreaterThanOrEqualTo(String value) {
            addCriterion("resourceType >=", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeLessThan(String value) {
            addCriterion("resourceType <", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeLessThanOrEqualTo(String value) {
            addCriterion("resourceType <=", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeLike(String value) {
            addCriterion("resourceType like", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeNotLike(String value) {
            addCriterion("resourceType not like", value, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeIn(List<String> values) {
            addCriterion("resourceType in", values, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeNotIn(List<String> values) {
            addCriterion("resourceType not in", values, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeBetween(String value1, String value2) {
            addCriterion("resourceType between", value1, value2, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andResourcetypeNotBetween(String value1, String value2) {
            addCriterion("resourceType not between", value1, value2, "resourcetype");
            return (Criteria) this;
        }

        public Criteria andBiztypeIsNull() {
            addCriterion("bizType is null");
            return (Criteria) this;
        }

        public Criteria andBiztypeIsNotNull() {
            addCriterion("bizType is not null");
            return (Criteria) this;
        }

        public Criteria andBiztypeEqualTo(Integer value) {
            addCriterion("bizType =", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotEqualTo(Integer value) {
            addCriterion("bizType <>", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeGreaterThan(Integer value) {
            addCriterion("bizType >", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bizType >=", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeLessThan(Integer value) {
            addCriterion("bizType <", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeLessThanOrEqualTo(Integer value) {
            addCriterion("bizType <=", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeIn(List<Integer> values) {
            addCriterion("bizType in", values, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotIn(List<Integer> values) {
            addCriterion("bizType not in", values, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeBetween(Integer value1, Integer value2) {
            addCriterion("bizType between", value1, value2, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotBetween(Integer value1, Integer value2) {
            addCriterion("bizType not between", value1, value2, "biztype");
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

        public Criteria andAttachmenttypeidIsNull() {
            addCriterion("attachmentTypeId is null");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidIsNotNull() {
            addCriterion("attachmentTypeId is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidEqualTo(Integer value) {
            addCriterion("attachmentTypeId =", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidNotEqualTo(Integer value) {
            addCriterion("attachmentTypeId <>", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidGreaterThan(Integer value) {
            addCriterion("attachmentTypeId >", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachmentTypeId >=", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidLessThan(Integer value) {
            addCriterion("attachmentTypeId <", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidLessThanOrEqualTo(Integer value) {
            addCriterion("attachmentTypeId <=", value, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidIn(List<Integer> values) {
            addCriterion("attachmentTypeId in", values, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidNotIn(List<Integer> values) {
            addCriterion("attachmentTypeId not in", values, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidBetween(Integer value1, Integer value2) {
            addCriterion("attachmentTypeId between", value1, value2, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andAttachmenttypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("attachmentTypeId not between", value1, value2, "attachmenttypeid");
            return (Criteria) this;
        }

        public Criteria andSuffixIsNull() {
            addCriterion("suffix is null");
            return (Criteria) this;
        }

        public Criteria andSuffixIsNotNull() {
            addCriterion("suffix is not null");
            return (Criteria) this;
        }

        public Criteria andSuffixEqualTo(String value) {
            addCriterion("suffix =", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotEqualTo(String value) {
            addCriterion("suffix <>", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThan(String value) {
            addCriterion("suffix >", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("suffix >=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThan(String value) {
            addCriterion("suffix <", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThanOrEqualTo(String value) {
            addCriterion("suffix <=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLike(String value) {
            addCriterion("suffix like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotLike(String value) {
            addCriterion("suffix not like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixIn(List<String> values) {
            addCriterion("suffix in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotIn(List<String> values) {
            addCriterion("suffix not in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixBetween(String value1, String value2) {
            addCriterion("suffix between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotBetween(String value1, String value2) {
            addCriterion("suffix not between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andRemotepathIsNull() {
            addCriterion("remotePath is null");
            return (Criteria) this;
        }

        public Criteria andRemotepathIsNotNull() {
            addCriterion("remotePath is not null");
            return (Criteria) this;
        }

        public Criteria andRemotepathEqualTo(String value) {
            addCriterion("remotePath =", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathNotEqualTo(String value) {
            addCriterion("remotePath <>", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathGreaterThan(String value) {
            addCriterion("remotePath >", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathGreaterThanOrEqualTo(String value) {
            addCriterion("remotePath >=", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathLessThan(String value) {
            addCriterion("remotePath <", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathLessThanOrEqualTo(String value) {
            addCriterion("remotePath <=", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathLike(String value) {
            addCriterion("remotePath like", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathNotLike(String value) {
            addCriterion("remotePath not like", value, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathIn(List<String> values) {
            addCriterion("remotePath in", values, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathNotIn(List<String> values) {
            addCriterion("remotePath not in", values, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathBetween(String value1, String value2) {
            addCriterion("remotePath between", value1, value2, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemotepathNotBetween(String value1, String value2) {
            addCriterion("remotePath not between", value1, value2, "remotepath");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidIsNull() {
            addCriterion("remoteServerUrlId is null");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidIsNotNull() {
            addCriterion("remoteServerUrlId is not null");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidEqualTo(Integer value) {
            addCriterion("remoteServerUrlId =", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidNotEqualTo(Integer value) {
            addCriterion("remoteServerUrlId <>", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidGreaterThan(Integer value) {
            addCriterion("remoteServerUrlId >", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidGreaterThanOrEqualTo(Integer value) {
            addCriterion("remoteServerUrlId >=", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidLessThan(Integer value) {
            addCriterion("remoteServerUrlId <", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidLessThanOrEqualTo(Integer value) {
            addCriterion("remoteServerUrlId <=", value, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidIn(List<Integer> values) {
            addCriterion("remoteServerUrlId in", values, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidNotIn(List<Integer> values) {
            addCriterion("remoteServerUrlId not in", values, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidBetween(Integer value1, Integer value2) {
            addCriterion("remoteServerUrlId between", value1, value2, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemoteserverurlidNotBetween(Integer value1, Integer value2) {
            addCriterion("remoteServerUrlId not between", value1, value2, "remoteserverurlid");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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
    }
}
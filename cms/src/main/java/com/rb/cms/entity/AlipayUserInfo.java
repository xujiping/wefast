package com.rb.cms.entity;

import java.io.Serializable;

public class AlipayUserInfo implements Serializable {

    private String clientListenId;

    private String userId;

    private String avatar;

    private String province;

    private String city;

    private String nickName;

    private String isStudentCertified;

    private String userType;

    private String userStatus;

    private String isCertified;

    private String gender;

    private static final long serialVersionUID = 1L;

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId == null ? null : clientListenId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified == null ? null : isStudentCertified.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Override
    public String toString() {
        return "AlipayUserInfo{" +
                "clientListenId='" + clientListenId + '\'' +
                ", userId='" + userId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isStudentCertified='" + isStudentCertified + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", isCertified='" + isCertified + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
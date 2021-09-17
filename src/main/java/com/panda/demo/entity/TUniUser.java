package com.panda.demo.entity;

import lombok.Data;

@Data
public class TUniUser {

    private String userId;
    private String userName;
    private String phone;
    private String email;
    private String password;
    private String state;
    private String userType;
    private String tenantId;
    private String processState;
    private String empId;
    private String empName;
    private String createBy;
    private java.sql.Timestamp createDate;
    private String updateBy;
    private java.sql.Timestamp updateDate;
    private long revision;
    private String avatar;
    private String gender;
    private String wechat;
    private String remarks;
    private String wxToken;

}

package com.example.entity;


public class JobExecutionLog {

  private long autoId;
  private String id;
  private String jobName;
  private String taskId;
  private String hostname;
  private String ip;
  private long shardingItem;
  private String executionSource;
  private String failureCause;
  private long isSuccess;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp completeTime;


  public long getAutoId() {
    return autoId;
  }

  public void setAutoId(long autoId) {
    this.autoId = autoId;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }


  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }


  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getShardingItem() {
    return shardingItem;
  }

  public void setShardingItem(long shardingItem) {
    this.shardingItem = shardingItem;
  }


  public String getExecutionSource() {
    return executionSource;
  }

  public void setExecutionSource(String executionSource) {
    this.executionSource = executionSource;
  }


  public String getFailureCause() {
    return failureCause;
  }

  public void setFailureCause(String failureCause) {
    this.failureCause = failureCause;
  }


  public long getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(long isSuccess) {
    this.isSuccess = isSuccess;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getCompleteTime() {
    return completeTime;
  }

  public void setCompleteTime(java.sql.Timestamp completeTime) {
    this.completeTime = completeTime;
  }

}

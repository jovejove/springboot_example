package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "JOB_STATUS_TRACE_LOG")
public class JobStatusTraceLog {

  @TableField("auto_id")
  private long autoId;
  @TableId
  private String id;
  private String jobName;
  private String originalTaskId;
  private String taskId;
  private String slaveId;
  private String source;
  private String executionType;
  private String shardingItem;
  private String state;
  private String message;
  private java.sql.Timestamp creationTime;


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


  public String getOriginalTaskId() {
    return originalTaskId;
  }

  public void setOriginalTaskId(String originalTaskId) {
    this.originalTaskId = originalTaskId;
  }


  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }


  public String getSlaveId() {
    return slaveId;
  }

  public void setSlaveId(String slaveId) {
    this.slaveId = slaveId;
  }


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public String getExecutionType() {
    return executionType;
  }

  public void setExecutionType(String executionType) {
    this.executionType = executionType;
  }


  public String getShardingItem() {
    return shardingItem;
  }

  public void setShardingItem(String shardingItem) {
    this.shardingItem = shardingItem;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public java.sql.Timestamp getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(java.sql.Timestamp creationTime) {
    this.creationTime = creationTime;
  }

}

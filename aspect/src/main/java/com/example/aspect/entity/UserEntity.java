package com.example.aspect.entity;

import com.example.aspect.annotation.OperationRecordDataInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: ljj
 * @date: 2022/4/18
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private String studentId;

    private String studentName;

    private String studentPhone;

    private String studentCardNo;
}

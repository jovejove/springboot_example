package com.example.es.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "talent_university")
public class TalentUniversity implements Serializable {

    private String id;
    private String code;

    //  @Field(type = FieldType.Text,analyzer = "ik_max_word")
    @Field(type = FieldType.Keyword, analyzer = "ik_smart")
    private String name;
    private String contacts;
    private String telephone;
    private String coremajor;
    private String orgid;
    private String empids;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;
    private Date validStart;
    private Date validEnd;
    @Field(type = FieldType.Keyword)
    private String creator;
    private String modificator;
    private String schoollevel;
    private String label;
    private String labeltext;
    private String invalid;
    private String version;

}

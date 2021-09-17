package com.panda.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BookVO {
    private String name;
    private String author;
    @JsonIgnore
    protected Float price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
}
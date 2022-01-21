package com.panda.excel.upload;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class CommonErrorExcelEntity implements Serializable {
        @ExcelProperty(index = 0)
        private String name;
        @ExcelProperty(index = 1)
        private String message;

        public CommonErrorExcelEntity(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
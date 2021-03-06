package com.panda.entity;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.List;

public class Root {
    private List<BodyInfos> bodyInfos;

    public void setBodyInfos(List<BodyInfos> bodyInfos) {
        this.bodyInfos = bodyInfos;
    }

    public List<BodyInfos> getBodyInfos() {
        return this.bodyInfos;
    }


    public static void main(String[] args) {
        String request = "{" +
                "  \"bodyInfos\": [" +
                "    {" +
                "      \"defitem48\": \"104000101\"," +
                "      \"defitem12\": \"104000101\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 20006127.42," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104000101\"," +
                "      \"defitem12\": \"104000101\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 2341477.68," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104000102\"," +
                "      \"defitem12\": \"104000102\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104000102\"," +
                "      \"defitem12\": \"104000102\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10400204\"," +
                "      \"defitem12\": \"10400204\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10400204\"," +
                "      \"defitem12\": \"10400204\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10400205\"," +
                "      \"defitem12\": \"10400205\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10400205\"," +
                "      \"defitem12\": \"10400205\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 64301.09," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010201\"," +
                "      \"defitem12\": \"104010201\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 92944," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010201\"," +
                "      \"defitem12\": \"104010201\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 12309.6," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010202\"," +
                "      \"defitem12\": \"104010202\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 9000," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010202\"," +
                "      \"defitem12\": \"104010202\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010204\"," +
                "      \"defitem12\": \"104010204\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 17500," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010204\"," +
                "      \"defitem12\": \"104010204\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 16470," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010212\"," +
                "      \"defitem12\": \"104010212\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 1200," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010212\"," +
                "      \"defitem12\": \"104010212\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010215\"," +
                "      \"defitem12\": \"104010215\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 18900," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010215\"," +
                "      \"defitem12\": \"104010215\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 1000," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010220\"," +
                "      \"defitem12\": \"104010220\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"104010220\"," +
                "      \"defitem12\": \"104010220\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10402\"," +
                "      \"defitem12\": \"10402\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 8000," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10402\"," +
                "      \"defitem12\": \"10402\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 1250," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10403\"," +
                "      \"defitem12\": \"10403\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 900," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"10403\"," +
                "      \"defitem12\": \"10403\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 5550," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040501\"," +
                "      \"defitem12\": \"1040501\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": -504842," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040501\"," +
                "      \"defitem12\": \"1040501\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 259013," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040502\"," +
                "      \"defitem12\": \"1040502\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040502\"," +
                "      \"defitem12\": \"1040502\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040503\"," +
                "      \"defitem12\": \"1040503\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040503\"," +
                "      \"defitem12\": \"1040503\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040504\"," +
                "      \"defitem12\": \"1040504\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040504\"," +
                "      \"defitem12\": \"1040504\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040505\"," +
                "      \"defitem12\": \"1040505\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040505\"," +
                "      \"defitem12\": \"1040505\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 128075.07," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040506\"," +
                "      \"defitem12\": \"1040506\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": -1000," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040506\"," +
                "      \"defitem12\": \"1040506\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040507\"," +
                "      \"defitem12\": \"1040507\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040507\"," +
                "      \"defitem12\": \"1040507\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040508\"," +
                "      \"defitem12\": \"1040508\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": -4189.6," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040508\"," +
                "      \"defitem12\": \"1040508\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": -500," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040509\"," +
                "      \"defitem12\": \"1040509\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040509\"," +
                "      \"defitem12\": \"1040509\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040510\"," +
                "      \"defitem12\": \"1040510\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040510\"," +
                "      \"defitem12\": \"1040510\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040511\"," +
                "      \"defitem12\": \"1040511\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"05\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 4208," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }," +
                "    {" +
                "      \"defitem48\": \"1040511\"," +
                "      \"defitem12\": \"1040511\"," +
                "      \"jobid\": \"\"," +
                "      \"defitem22\": \"09\"," +
                "      \"defitem20\": \"11\"," +
                "      \"amount\": 0," +
                "      \"pk_group\": \"时代集团\"," +
                "      \"jkbxr\": \"DC1\"," +
                "      \"dwbm\": \"DC0001\"," +
                "      \"paytarget\": \"员工\"," +
                "      \"deptid\": \"12\"," +
                "      \"receiver\": \"DC1\"" +
                "    }" +
                "  ]" +
                "}";


        Root root = JSONObject.parseObject(request, Root.class);

        List<BodyInfos> bodyInfos = root.getBodyInfos();

        System.out.println(bodyInfos);


        BigDecimal bigDecimal = new BigDecimal(0);
        for (BodyInfos infos : bodyInfos) {
            bigDecimal = bigDecimal.add(infos.getAmount());

            System.out.println(infos.getAmount());
        }


        System.out.println(bigDecimal);
    }
}
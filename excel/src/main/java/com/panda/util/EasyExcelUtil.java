package com.panda.util;

import com.alibaba.excel.EasyExcel;
import com.panda.excel.resp.BizException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

/**
 * 阿里巴巴生成excel工具类
 * @describe
 * @Date 2019/7/19 16:52
 */
public class EasyExcelUtil {

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param classes 映射实体类的class
     */
    public static void  writeExcelExcludeColumn(HttpServletResponse response, List<?> list,
                                   String fileName, String sheetName, Class classes, Set<String> excludeColumnFiledNames)throws Exception  {
        OutputStream outputStream = getOutputStream(fileName, response);
        try {
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(outputStream, classes).excludeColumnFiledNames(excludeColumnFiledNames).sheet(sheetName).doWrite(list);
        } catch (Exception e) {
            throw new BizException("导出失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param classes 映射实体类的class
     */
    public static void  writeExcel(HttpServletResponse response, List<?> list,
                                   String fileName, String sheetName, Class classes)throws Exception  {
        OutputStream outputStream = getOutputStream(fileName, response);
        try {
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(outputStream, classes).sheet(sheetName).doWrite(list);
        } catch (Exception e) {
            throw new BizException("导出失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param heads sheet头
     */
    public static void  writeExcel(HttpServletResponse response, List<?> list,
                                   String fileName, String sheetName, List<List<String>> heads)throws Exception  {
        OutputStream outputStream = getOutputStream(fileName, response);
        try {
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(outputStream).sheet(sheetName).head(heads).doWrite(list);
        } catch (Exception e) {
            throw new BizException("导出失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




    /**
     * 导出文件时为Writer生成OutputStream
     *
     * @param fileName
     * @param response
     * @return
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

}

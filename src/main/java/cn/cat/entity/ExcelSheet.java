package cn.cat.entity;

import lombok.Data;

import java.util.List;

/**
 * @author cat
 * @date 2022/10/25
 * @description sheet实体类
 */
@Data
public class ExcelSheet {
    private int sheetNo;
    private String sheetName;
    private List<Student> studentList;
}

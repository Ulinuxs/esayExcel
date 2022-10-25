package cn.cat;

import lombok.Data;

import java.util.List;

@Data
public class ExcelSheet {
    private int sheetNo;
    private String sheetName;
    private List<Student> studentList;
}

package cn.cat;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Student {
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
    @ExcelProperty(value = "学生年龄",index = 2)
    private int age;
}

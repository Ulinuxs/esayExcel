package cn.cat.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author cat
 * @date 2022/10/25
 * @description 学生实体类
 */
@Data
public class Student {
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
    @ExcelProperty(value = "学生年龄",index = 2)
    private int age;
}

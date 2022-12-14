package cn.cat;

import cn.cat.entity.ExcelSheet;
import cn.cat.entity.Student;
import cn.cat.listener.DefaultExcelListener;
import cn.cat.listener.StudentListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cat
 * @date 2022/10/25
 * @description 启动类
 */
public class Main {
    public static void main(String[] args) {
        String filePath="D://student.xlsx";
        List<Student> studentList =new ArrayList<>();
        for (int i=1;i<=10;i++){
            Student student = new Student();
            student.setSno(i);
            student.setName("学生"+i);
            student.setAge(i+12);
            studentList.add(student);
        }


        //生成单个sheet并写入数据
        EasyExcel.write(filePath, Student.class).sheet().doWrite(studentList);

        //生成多个sheet并写入数据
        try (ExcelWriter excelWriter = EasyExcel.write(filePath, Student.class).build()) {
            for (int i=1;i<=2;i++){
                WriteSheet writeSheet = EasyExcel.writerSheet("学生列表"+i).build();
                excelWriter.write(studentList,writeSheet);
            }
            excelWriter.finish();
        }


        //读取第一个sheet中的数据
        EasyExcel.read(filePath,Student.class,new DefaultExcelListener()).sheet().doRead();

        //读取所有sheet中的数据
        EasyExcel.read(filePath, Student.class,new DefaultExcelListener()).doReadAll();

        //读取指定sheet中的数据
        try (ExcelReader excelReader = EasyExcel.read(filePath).build()) {
            ReadSheet readSheet1=EasyExcel.readSheet(0).head(Student.class).registerReadListener(new DefaultExcelListener()).build();
            ReadSheet readSheet2=EasyExcel.readSheet(1).head(Student.class).registerReadListener(new DefaultExcelListener()).build();
            excelReader.read(readSheet1, readSheet2);
            excelReader.finish();
        }

        //读取指定sheet中的数据并用集合存储数据
        List<Student> studentList1=EasyExcel.read(filePath,Student.class,new DefaultExcelListener()).sheet(0).doReadSync();
        List<Student> studentList2=EasyExcel.read(filePath,Student.class,new DefaultExcelListener()).sheet(1).doReadSync();

        for (int i=0;i<studentList1.size();i++){
            if (studentList1.get(i).equals(studentList2.get(i))){
                System.out.print(studentList1.get(i).getName()+" equals "+studentList2.get(i).getName()+":");
                System.out.println(true);
            }

            if (studentList1.get(i)==studentList2.get(i)){
                System.out.print(studentList1.get(i).getName()+"=="+studentList2.get(i).getName()+":");
                System.out.println(true);
            }

        }

        //读取所有数据 并以sheet分组
        List<ExcelSheet> excelSheetList=new ArrayList<>();
        try (ExcelReader excelReader=EasyExcel.read(filePath).build()){
            List<ReadSheet> readSheetList=excelReader.excelExecutor().sheetList();
            for (ReadSheet readSheet:readSheetList){
                ExcelSheet excelSheet=new ExcelSheet();
                excelSheet.setSheetNo(readSheet.getSheetNo());
                excelSheet.setSheetName(readSheet.getSheetName());
                List<Student> students=EasyExcel.read(filePath).sheet(excelSheet.getSheetNo(),excelSheet.getSheetName()).doReadSync();
                excelSheet.setStudentList(students);
                excelSheetList.add(excelSheet);
            }
            excelReader.finish();
        }

        for (ExcelSheet excelSheet:excelSheetList){
            System.out.println(excelSheet);
        }

    }
}
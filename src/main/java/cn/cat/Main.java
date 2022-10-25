package cn.cat;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

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
        }


        //读取第一个sheet中的数据
        EasyExcel.read(filePath,Student.class,new StudentListener()).sheet().doRead();

        //读取所有sheet中的数据
        EasyExcel.read(filePath, Student.class,new StudentListener()).doReadAll();

        //读取指定sheet中的数据
        try (ExcelReader excelReader = EasyExcel.read(filePath).build()) {
            ReadSheet readSheet1=EasyExcel.readSheet(0).head(Student.class).registerReadListener(new StudentListener()).build();
            ReadSheet readSheet2=EasyExcel.readSheet(1).head(Student.class).registerReadListener(new StudentListener()).build();
            excelReader.read(readSheet1, readSheet2);
        }

        //读取指定sheet中的数据并用集合存储数据
        List<Student> studentList1=EasyExcel.read(filePath,Student.class,null).sheet(0).doReadSync();
        List<Student> studentList2=EasyExcel.read(filePath,Student.class,null).sheet(1).doReadSync();

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
        ExcelReader excelReader=EasyExcel.read(filePath).build();
        List<ExcelSheet> excelSheetList=new ArrayList<>();
        List<ReadSheet> readSheetList=excelReader.excelExecutor().sheetList();
        for (ReadSheet readSheet:readSheetList){
            ExcelSheet excelSheet=new ExcelSheet();
            excelSheet.setSheetNo(readSheet.getSheetNo());
            excelSheet.setSheetName(readSheet.getSheetName());
            List<Student> students=EasyExcel.read(filePath).sheet(excelSheet.getSheetNo(),excelSheet.getSheetName()).doReadSync();
            excelSheet.setStudentList(students);
            excelSheetList.add(excelSheet);
        }

        for (ExcelSheet excelSheet:excelSheetList){
            System.out.println(excelSheet);
        }

    }
}
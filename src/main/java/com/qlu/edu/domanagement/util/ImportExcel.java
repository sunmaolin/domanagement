package com.qlu.edu.domanagement.util;

import com.qlu.edu.domanagement.entity.Student;
import com.qlu.edu.domanagement.service.DormitoryService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ImportExcel {
    private static final String XLSX = "xlsx";
    private static final String XLS = "xls";
    private MultipartFile file; //文件
    private String fileName; //文件名
    private String suffix; //后缀

    public ImportExcel(MultipartFile file) {
        this.file = file;
        this.fileName = file.getOriginalFilename();
        this.suffix = this.fileName.substring(fileName.lastIndexOf(".")+1);
    }


    public boolean typeError(){
        if (StringUtils.isEmpty(suffix) || !XLSX.equals(suffix) && !XLS.equals(suffix)){
            return false;
        }
        return true;
    }

    public List importStudent(DormitoryService service) throws IOException {
        List<Student> allStudent = new ArrayList<>();
        //获取工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        //获取第一行
        XSSFRow firstRow = sheet.getRow(0);
        boolean isTrue = true;
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            XSSFCell cell = firstRow.getCell(i);
            String columnName = cell.getStringCellValue();//获取列名
            switch (i){
                case 0:
                   isTrue ="宿舍楼".equals(columnName)?true:false;
                   break;
                case 1:
                    isTrue ="宿舍".equals(columnName)?true:false;
                    break;
                case 2:
                    isTrue ="学号".equals(columnName)?true:false;
                    break;
                case 3:
                    isTrue ="姓名".equals(columnName)?true:false;
                    break;
                case 4:
                    isTrue ="性别".equals(columnName)?true:false;
                    break;
                case 5:
                    isTrue ="电话".equals(columnName)?true:false;
                    break;
                case 6:
                    isTrue ="专业".equals(columnName)?true:false;
                    break;
                case 7:
                    isTrue ="班级".equals(columnName)?true:false;
                    break;
            }
        }
        if (!isTrue){
            throw new RuntimeException("excel格式错误！");
        }
        //从第二行开始
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            Student student = new Student();
            Integer fid = 0;
            for (int j = 0; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                switch (j){
                    case 0:
                        String fname = getValue(cell);
                        fid = service.findFidByFname(fname);
                        break;
                    case 1:
                        String dname = getValue(cell);
//                        dname = dname.substring(0,dname.lastIndexOf("."));
                        Integer did = service.findDidByFidAndDname(fid,dname);
                        student.setDid(did);
                        break;
                    case 2:
                        String sid = getValue(cell);
                        student.setSid(sid);
                        break;
                    case 3:
                        String sname = getValue(cell);
                        student.setSname(sname);
                        break;
                    case 4:
                        String ssex = getValue(cell);
                        student.setSsex(ssex);
                        break;
                    case 5:
                        String sphone = getValue(cell);
                        student.setSphone(sphone);
                        break;
                    case 6:
                        String sprofessional = getValue(cell);
                        student.setSprofessional(sprofessional);
                        break;
                    case 7:
                        String sclass = getValue(cell);
                        student.setSclass(sclass);
                }
            }
            allStudent.add(student);
        }
        return allStudent;
    }

    private String getValue(XSSFCell cell){
        if(cell.getCellType()==XSSFCell.CELL_TYPE_BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
            DecimalFormat df = new DecimalFormat("0");//小数解析，防止科学计数
            return String.valueOf(df.format(cell.getNumericCellValue()));
        }else{
            return cell.getStringCellValue();
        }
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package export;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author huynh
 */
public class NhanVienExcel {
 
    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Nhân viên");
 
            XSSFRow row = null;
            Cell cell = null;
 
            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh sách nhân viên");
 
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ nhân viên");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tên nhân viên");
            
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Lương");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Chức vụ");
            cell = row.createCell(9, CellType.STRING);
            
            NhanVienBUS khdao = new NhanVienBUS();
            //HocVienService nhanvienService = new HocVienServiceImpl();
 
            List<NhanVienDTO> listItem = NhanVienDAO.getList();
 
            for (int i = 0; i < listItem.size(); i++) {
                NhanVienDTO nhanvien = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(nhanvien.getManv());
                row.createCell(2).setCellValue(nhanvien.getHo().toString());
                row.createCell(3).setCellValue(nhanvien.getTen().toString());
                row.createCell(4).setCellValue(nhanvien.getGioitinh());
                row.createCell(5).setCellValue(nhanvien.getNgaysinh());
                row.createCell(6).setCellValue(nhanvien.getDiachi());
                row.createCell(7).setCellValue(nhanvien.getLuong());
                row.createCell(8).setCellValue(nhanvien.getChucvu());
                
            }
 
            FileOutputStream out = new FileOutputStream(new File("ExcelExport/nhanvien.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
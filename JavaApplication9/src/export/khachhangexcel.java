/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package export;

/**
 *
 * @author huynh
 */
import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 
public class khachhangexcel {
 
    public void xuatfile()
    {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Khách Hàng");
 
            XSSFRow row = null;
            Cell cell = null;
 
            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh sách khách hàng");
 
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ khách hàng");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tên khách hàng");
            
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell = row.createCell(7, CellType.STRING);
            
            KhachHangBUS khdao = new KhachHangBUS();
           
            List<KhachHangDTO> listItem = KhachHangDAO.getList();
 
            for (int i = 0; i < listItem.size(); i++) {
                KhachHangDTO khachhang = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(khachhang.getMakh());
                row.createCell(2).setCellValue(khachhang.getHo().toString());
                row.createCell(3).setCellValue(khachhang.getTen().toString());
                row.createCell(4).setCellValue(khachhang.getSodt());
                row.createCell(5).setCellValue(khachhang.getNgaysinh());
                row.createCell(6).setCellValue(khachhang.getGioitinh());
            }
 
            FileOutputStream out = new FileOutputStream(new File("ExcelExport/khachhang.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        khachhangexcel khachhang = new khachhangexcel();
        khachhang.xuatfile();
    }
 
}
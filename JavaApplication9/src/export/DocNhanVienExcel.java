/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package export;


import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DocNhanVienExcel {
  public static void main(String[] args) {
    try {
      FileInputStream excelFile = new FileInputStream(new File("D:/nhanvien_SQL.xlsx"));
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      DataFormatter fmt = new DataFormatter();
      Iterator<Row> iterator = datatypeSheet.iterator();
      Row firstRow = iterator.next();
      Cell firstCell = firstRow.getCell(0);
      
      System.out.println(firstCell.getStringCellValue());
      
      List<NhanVienDTO> DSNhanVienDTO = new ArrayList<NhanVienDTO>();
      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        NhanVienDTO nhanVienDTO  = new NhanVienDTO();
     
        nhanVienDTO.setManv(currentRow.getCell(1).getStringCellValue());
        nhanVienDTO.setHo(currentRow.getCell(2).getStringCellValue());
        nhanVienDTO.setTen(currentRow.getCell(3).getStringCellValue());
        nhanVienDTO.setGioitinh(currentRow.getCell(4).getStringCellValue());
        nhanVienDTO.setNgaysinh(currentRow.getCell(5).getStringCellValue());
        nhanVienDTO.setDiachi(currentRow.getCell(6).getStringCellValue());
        nhanVienDTO.setLuong(currentRow.getCell(7).getStringCellValue());
        nhanVienDTO.setChucvu(currentRow.getCell(8).getStringCellValue());
        
        DSNhanVienDTO.add(nhanVienDTO);
      }
      for (NhanVienDTO nhanVienDTO : DSNhanVienDTO) {
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        nhanVienBUS.Insert(nhanVienDTO);
      }
      workbook.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
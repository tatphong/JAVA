/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Tour;
import DAO.DAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pmphanlop.DSTour;
/**
 *
 * @author tattr
 */
public class QLTour extends Tour{
    DefaultTableModel model;
    
    public QLTour(int idTour, String tenTour, int songay, String ngaybatdau) {
        super(idTour, tenTour, songay, ngaybatdau);
    }
    
    public QLTour(){}
    
    public DefaultTableModel getDSTour(JTable TourTable){
        ResultSet rs = DAO.select("*", "tour", null);
//        DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model = (DefaultTableModel) TourTable.getModel();
//        Object[] columm = {"Mã Tour", "Tên Tour", "Thời gian tour", "Ngày khởi hành", "Giá"};
//        model.setColumnIdentifiers(columm);
        Object[] rows = new Object[10];
        try {
            while (rs.next()) {
                //System.out.println(rows[0]);
                rows[0] = rs.getInt("idtour");
                rows[1] = rs.getString("tentour");
                rows[2] = rs.getString("thoigiantour");
                rows[3] = rs.getString("ngaykhoihanh");
                rows[4] = 0;
                System.out.print(rows);
                model.addRow(rows);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSTour.class.getName()).log(Level.SEVERE, null, ex);
        }
//        TourTable.setModel(model);
        return model;
    }
    
    public void addDB(Tour a){
        String insert_value = "N'"+a.getTenTour()+"',"+a.getSongay()+",'"+a.getNgaybatdau()+"'";
        DAO.insert("tour", "tentour, thoigiantour, ngaykhoihanh" , insert_value);
    }
    
    
}

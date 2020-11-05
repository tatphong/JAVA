/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.DAO;
import java.sql.Date;

/**
 *
 * @author tattr
 */
public class Tour {
    int idTour;
    String tenTour;
    int songay;
    String ngaybatdau;

    public Tour(int idTour, String tenTour, int songay, String ngaybatdau) {
        this.idTour = idTour;
        this.tenTour = tenTour;
        this.songay = songay;
        this.ngaybatdau = ngaybatdau;
    }
    public Tour(){
        
    }
    
//    public void add(Tour a){
//         DAO.insert("tour", "");
//    }
    
    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public int getSongay() {
        return songay;
    }

    public void setSongay(int songay) {
        this.songay = songay;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }
    
    
    
}

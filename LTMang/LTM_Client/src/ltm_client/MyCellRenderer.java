package ltm_client;


import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tattr
 */
class MyCellRenderer extends DefaultListCellRenderer {
    public static final String HTML_1 = "<html><body style='font-size:12px; padding-bottom:5px; padding-top:5px; width: ";
    public static final String HTML_2 = "px; text-align:left; margin-bottom:2px; padding-left:10px; padding-top: 5px; padding-bottom:5px; color:white; background-color:gray'><strong>";
//    public static final String HTML_22 = "px;'><div style='width:min-content; float:right; margin-left:100px; text-align:right; color:white; background-color:blue'>";
    public static final String HTML_22 = "px;'><div style='width:100%; margin-bottom:2px; padding-right:10px; padding-top: 5px; padding-bottom:5px; margin-left:100px; color:white; background-color:blue; text-align:right'><strong>";
    public static final String HTML_3 = "</strong></html>";
    public static final String HTML_33 = "</strong></div></html>";
    private int width;

    public MyCellRenderer(int width) {
       this.width = width;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String text = "";
        if (index % 2 == 1)
            //chuoi tra ve ben trai
            text = HTML_1 + String.valueOf(width-width*0.2) + HTML_2 + value.toString() + HTML_3;
        else
            //chuoi gui di ben phai
            text = HTML_1 + String.valueOf(width) + HTML_22 + value.toString() + HTML_33;
        return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
    }

}
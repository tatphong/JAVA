/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CleanData;

import java.util.Date;

/**
 *
 * @author tattr
 */
public class CleanDateTime {
    
    public String Time2String(Date date){
        
    }
    
    public String Time2String()
    {
        String d = null;
        if (date.getDate() < 10) d = "0" + String.valueOf(date.getDate());
        else d = String.valueOf(date.getDate());
        String m = null;
        if (date.getMonth() < 10) m = "0" + String.valueOf(date.getMonth() + 1);
        else m = String.valueOf(date.getMonth() + 1);
        String result = d + "/" + m + "/" + String.valueOf(date.getYear() + 1900);
        return result;
    }
}

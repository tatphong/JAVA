package BUS;

import DAO.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DSPHIEUCHI
{
    private ArrayList<PHIEUCHI> list = new ArrayList<>();
    
    public DSPHIEUCHI()
    {
        this.importDS();
    }

    public ArrayList<PHIEUCHI> getList() {return list;}
    public void setList(ArrayList<PHIEUCHI> list) {this.list = list;}
    
    private void importDS()
    {
        ResultSet res;
        res = DAO.select("*","PHIEUCHI",null);
        try
        {
            while (res.next())
            {
                this.list.add(new PHIEUCHI(res.getInt(1), res.getDate(2), res.getInt(3), res.getString(4)));
            }
        }
        catch (SQLException ex) {}
    }
    
    public ArrayList<PHIEUCHI> importDS_theothang(int month, int year)
    {
        ResultSet res;
        res = DAO.select(
                "*",
                "PHIEUCHI",
                "month(thoigian) = " + month + " and year(thoigian) = " + year);
        ArrayList<PHIEUCHI> dsthang = new ArrayList<>();
        try
        {
            while (res.next())
            {
                dsthang.add(new PHIEUCHI(res.getInt(1), res.getDate(2), res.getInt(3), res.getString(4)));
            }
        }
        catch (SQLException ex) {}
        return dsthang;
    }
    
    public void add(Date date, int sotien, String ghichu)
    {
        int day = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        
        String str_month = month<10?"0"+String.valueOf(month):String.valueOf(month);
        String str_day = day<10?"0"+String.valueOf(day):String.valueOf(day);
        
        String date_format = String.valueOf(year) + "-" + str_month + "-" + str_day;
        System.out.println(date_format);
        
        int hour = date.getHours();
        int min = date.getMinutes();
        int sec = date.getSeconds();
        
        String str_hour = hour<10?"0"+String.valueOf(hour):String.valueOf(hour);
        String str_min = min<10?"0"+String.valueOf(min):String.valueOf(min);
        String str_sec = sec<10?"0"+String.valueOf(sec):String.valueOf(sec);
        
        String time_format = str_hour + ":" + str_min + ":" + str_sec;
        String str = String.format("'" + date_format + "T" + time_format + "','" + sotien + "','" + ghichu + "'");
        System.out.println(str);
        DAO.insert("PHIEUCHI", "thoigian, tongtien, ghichu", str);
        this.list.clear();
        this.importDS();
    }
    
    public void delete(int id)
    {
        for (int i = 0; i < this.list.size(); i++)
        {
            if (this.list.get(i).getID() == id)
            {
                this.list.remove(i);
                break;
            }
        }
        String str1 = String.format("idPC = " + id);
        DAO.delete("PHIEUCHI", str1);
        
        DSCTPHIEUCHI ds = new DSCTPHIEUCHI();
        for (int i = 0; i < ds.getList().size(); i++)
        {
            if (ds.getList().get(i).getIDPC() == id)
            {
                ds.getList().remove(i);
            }
        }
        String str2 = String.format("idPC = " + id);
        DAO.delete("CTPHIEUCHI", str2);
    }
    
    public ArrayList<PHIEUCHI> searchID(int id)
    {
        ArrayList<PHIEUCHI> result = new ArrayList<PHIEUCHI>();
        for (int i = 0; i < this.list.size(); i++)
        {
            if (this.list.get(i).getID() == id)
            {
                result.add(this.list.get(i));
                break;
            }
        }
        return result;
    }
    
    
    public ArrayList<PHIEUCHI> searchDATE(Date date)
    {
        ArrayList<PHIEUCHI> result = new ArrayList<>();
       String d = null;
        if (date.getDate() < 10) d = "0" + String.valueOf(date.getDate());
        else d = String.valueOf(date.getDate());
        String m = null;
        if (date.getMonth() < 10) m = "0" + String.valueOf(date.getMonth());
        else m = String.valueOf(date.getMonth());
        String str = d + "/" + m + "/" + String.valueOf(date.getYear());;
        
        for (int i = 0; i < this.list.size(); i++)
        {
            if (this.list.get(i).toString().equals(str) == true)
                result.add(this.list.get(i));
        }
        
        return result;
    }
    
    
    
    
    public void test(ArrayList<PHIEUCHI> test)
    {
        for (int i = 0; i < test.size(); i++)
        {
            System.out.print(test.get(i).getID() + " == ");
            System.out.print(test.get(i).getDate().toString() + " == ");
            System.out.print(test.get(i).getSotien() + " == ");
            System.out.print(test.get(i).getGhiChu() + "\n");
        }
    }
}

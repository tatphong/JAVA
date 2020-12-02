/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.jsoup.nodes.Document;

public class test {
    public static void main(String[] args)  {
        try {
            String key = "4ca633b9d470650ea1ac927fdfe79ad3";
            String namecity="vinh long";
                Document doc = org.jsoup.Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q="+namecity+"&appid="+key)
                .userAgent("Mozilla").ignoreContentType(true).get();
                String pre = doc.body().text();
                Type classOft = new TypeToken<Example>(){}.getType();
                Gson gson = new Gson();
                Example ex = gson.fromJson(pre, classOft);
                System.out.println(ex.toString());
                // độ ở đây là độ k ko phải độ c >>> chuyển thì trừ 273.15
        } catch (Exception e) {
            System.out.println("API KO CÓ TỈNH NÀY");
        }
          
                
    }
    
}

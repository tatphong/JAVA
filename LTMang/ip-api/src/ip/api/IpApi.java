/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.jsoup.nodes.Document;

/**
 *
 * @author Admin
 */
public class IpApi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String ip = "8.8.8.8";
        Document doc = org.jsoup.Jsoup.connect("http://ip-api.com/json/"+ip)
                .userAgent("Mozilla").ignoreContentType(true).get();
        String pre = doc.body().text();
        Type classOft = new TypeToken<ip>(){}.getType();
        Gson gson = new Gson();
        ip ip1 = gson.fromJson(pre, classOft);
        System.out.println(ip1.getOrg());
    }
    
}

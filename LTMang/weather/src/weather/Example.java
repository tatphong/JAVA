/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

@SerializedName("weather")
@Expose
private List<Weather> weather = null;
@SerializedName("main")
@Expose
private Main main;
@SerializedName("wind")
@Expose
private Wind wind;

public List<Weather> getWeather() {
return weather;
}

public void setWeather(List<Weather> weather) {
this.weather = weather;
}

public Main getMain() {
return main;
}

public void setMain(Main main) {
this.main = main;
}

public Wind getWind() {
return wind;
}

public void setWind(Wind wind) {
this.wind = wind;
}
    @Override
    public String toString() {
        return "Example{" + "weather=" + weather + ", main=" + main + ", wind=" + wind + '}';
    }

}
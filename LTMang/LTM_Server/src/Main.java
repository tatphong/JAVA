/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main {

@SerializedName("temp")
@Expose
private Float temp;
@SerializedName("feels_like")
@Expose
private Float feelsLike;
@SerializedName("temp_min")
@Expose
private Float tempMin;
@SerializedName("temp_max")
@Expose
private Float tempMax;
@SerializedName("pressure")
@Expose
private Integer pressure;
@SerializedName("humidity")
@Expose
private Integer humidity;

public Float getTemp() {
return temp;
}

public void setTemp(Float temp) {
this.temp = temp;
}

public Float getFeelsLike() {
return feelsLike;
}

public void setFeelsLike(Float feelsLike) {
this.feelsLike = feelsLike;
}

public Float getTempMin() {
return tempMin;
}

public void setTempMin(Float tempMin) {
this.tempMin = tempMin;
}

public Float getTempMax() {
return tempMax;
}

public void setTempMax(Float tempMax) {
this.tempMax = tempMax;
}

public Integer getPressure() {
return pressure;
}

public void setPressure(Integer pressure) {
this.pressure = pressure;
}

public Integer getHumidity() {
return humidity;
}

public void setHumidity(Integer humidity) {
this.humidity = humidity;
}

//    @Override
//    public String toString() {
//        return "Main{" + "temp=" + temp + ", feelsLike=" + feelsLike + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", pressure=" + pressure + ", humidity=" + humidity + '}';
//    }

    @Override
    public String toString() {
        return " temp=" + temp + ", feelsLike=" + feelsLike + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", pressure=" + pressure + ", humidity=" + humidity;
    }

}
package br.edu.ifsc.alunos.clientesd.resources.generics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import br.edu.ifsc.alunos.clientesd.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl {

    public static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            String lDate = element.getAsString();
            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            try {
                Date lDateCreated = lFormat.parse(lDate);
                return lDateCreated;
            } catch (ParseException exp) {
                String datereip = lDate.replace("/Date(", "").replace(")/", "");
                Long timeInMillis = Long.valueOf(datereip);
                Calendar lCalendar = Calendar.getInstance();
                lCalendar.setTimeInMillis(timeInMillis);
                long startTimeMillis = (lCalendar.get(Calendar.HOUR_OF_DAY) * 1000 * 60 * 60) + (lCalendar.get(Calendar.MINUTE) * 1000 * 60);
                startTimeMillis -= getTimeOffset(startTimeMillis);
                Date lDateTeste = new Date(startTimeMillis);
                return new Date(timeInMillis);
            }
        }
    }

    private static int getTimeOffset(long time) {
        TimeZone tz = TimeZone.getDefault();
        return tz.getOffset(time);
    }

    public Retrofit buildRetrofit(String aBaseUrl) {
//        final Gson lGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ").create();
        Gson lGson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        HttpLoggingInterceptor lInterceptor = new HttpLoggingInterceptor();
        lInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient lBuild = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(lInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(aBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(lGson))
                .client(lBuild)
                .build();
    }

    public static Map<String, String> getParams(Object aRequest) {
        String lJson = new Gson().toJson(aRequest);
//      TODO  Estava transformando todos os tipos de dados em String e com outras tipagens dava problema, por isso a alteração.
        Type lType = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(lJson, lType);
    }
}

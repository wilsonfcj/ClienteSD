package br.edu.ifsc.alunos.clientesd.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String convertData(Date aDate, String formato) {
        SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat(formato);//"dd/MM/yyyy");
        return lSimpleDateFormat.format(aDate);
    }

    public static String getDate(String formato) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat(formato);//"dd/MM/yyyy");
        return lSimpleDateFormat.format(calendar.getTime());
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}

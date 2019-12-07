package br.edu.ifsc.alunos.clientesd.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String convertData(Date aDate, String formato) {
        SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat(formato);//"dd/MM/yyyy");
        return lSimpleDateFormat.format(aDate);
    }
}

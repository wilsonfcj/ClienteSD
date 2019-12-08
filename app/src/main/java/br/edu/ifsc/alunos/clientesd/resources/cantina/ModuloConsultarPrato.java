package br.edu.ifsc.alunos.clientesd.resources.cantina;

import android.os.AsyncTask;

import br.edu.ifsc.alunos.clientesd.modelos.cardapio.Cardapio;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.ListenerService;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuloConsultarPrato {

    private ListenerCardapio listenerCardapio;

    public ModuloConsultarPrato(ListenerCardapio listenerCardapio) {
        this.listenerCardapio = listenerCardapio;
    }

    public void consultar(int dia) {
        new  AsynTaskCardapioPorDia().execute(dia);
    }

    class AsynTaskCardapioPorDia extends AsyncTask<Integer, String , Cardapio> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cardapio doInBackground(Integer... dias) {
            int dia = dias[0];
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_CANTINA);
            CantinaAPI lApi = lRetrofit.create(CantinaAPI.class);

            try {
                Call<Cardapio> lObjectCall = lApi.consultarCardapio(dia);
                Response<Cardapio> lExecute = lObjectCall.execute();
                if (lExecute.body() != null) {
                    return lExecute.body();
                }
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Cardapio cardapio) {
            super.onPostExecute(cardapio);
            if(cardapio != null) {
                listenerCardapio.success(cardapio);
            }  else {
                listenerCardapio.error("Não foi possível carregar as informações");
            }
        }
    }

    public interface ListenerCardapio extends ListenerService<Cardapio> {
        @Override
        void success(Cardapio objeto);

        @Override
        void error(String msmErro);
    }
}

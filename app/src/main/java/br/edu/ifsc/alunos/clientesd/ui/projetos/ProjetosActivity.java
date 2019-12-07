package br.edu.ifsc.alunos.clientesd.ui.projetos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.MainActivity;
import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import br.edu.ifsc.alunos.clientesd.resources.projetos.ProjetosAPI;
import br.edu.ifsc.alunos.clientesd.ui.projetos.adapter.AdapterProjetos;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProjetosActivity extends BaseActivty {

    private RecyclerView mRecyclerView;
    private AdapterProjetos mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Projeto> mProjetos = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayoutErro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_projetos);
        swipeRefreshLayout.setRefreshing(true);
        new AsynTaskConsultarProjetos().execute();
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        setTitleToolbar("Projetos de pesquisa");
        configToolbar();
        linearLayoutErro = findViewById(R.id.container_msm_erro);
        mRecyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        createList();
    }

    private void configToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getToolbar().setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        getToolbar().setContentInsetsAbsolute(0, 0);
        getToolbar().setContentInsetStartWithNavigation(0);
        getToolbar().setContentInsetStartWithNavigation(0);
    }

    @Override
    public void mapActionComponents() {
        super.mapActionComponents();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsynTaskConsultarProjetos().execute();
            }
        });

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void createList() {
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterProjetos(ProjetosActivity.this, mProjetos);
        mRecyclerView.setAdapter(mAdapter);
    }


   class AsynTaskConsultarProjetos extends AsyncTask< String, String , List<Projeto>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Projeto> doInBackground(String... strings) {
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_PROJETOS);
            ProjetosAPI lApi = lRetrofit.create(ProjetosAPI.class);

            try {
                Call<ResponseBase<List<Projeto>>> lObjectCall = lApi.consultarProjetos();
                Response<ResponseBase<List<Projeto>>> lExecute = lObjectCall.execute();
                if (lExecute.body().isSucesso()) {
                   return lExecute.body().getData();
                }
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Projeto> projetos) {
            super.onPostExecute(projetos);
            displayList(projetos);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void displayList(List<Projeto> projetos) {
        if(projetos != null) {
            mAdapter.addItens(projetos);
            linearLayoutErro.setVisibility(View.INVISIBLE);
        } else {
            linearLayoutErro.setVisibility(View.VISIBLE);
        }
    }
}

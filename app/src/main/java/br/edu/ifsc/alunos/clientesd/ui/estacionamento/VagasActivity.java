package br.edu.ifsc.alunos.clientesd.ui.estacionamento;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.resources.estacionamento.ModuloConsultarVagas;
import br.edu.ifsc.alunos.clientesd.ui.estacionamento.adapter.AdapterVagas;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;

public class VagasActivity extends BaseActivty {

    private ModuloConsultarVagas moduloConsultarVagas;
    private RecyclerView mRecyclerView;
    private AdapterVagas mAdapterVagas;
    private List<Vaga> mVagas = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayoutErro;
    private boolean livres = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        moduloConsultarVagas.consultar();
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        if(getIntent().getExtras() != null)
            livres = getIntent().getExtras().getBoolean("livres", false);
        mRecyclerView = findViewById(R.id.recycler_view);
        setTitleToolbar(livres ? "Vagas Disponíveis" : "Todas as Vagas");
        configToolbar();
        linearLayoutErro = findViewById(R.id.container_msm_erro);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapterVagas = new AdapterVagas(VagasActivity.this, mVagas);
        mRecyclerView.setAdapter(mAdapterVagas);
    }

    @Override
    public void mapActionComponents() {
        super.mapActionComponents();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moduloConsultarVagas.consultar();
            }
        });

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void createRestListener() {
        moduloConsultarVagas = new ModuloConsultarVagas(new ModuloConsultarVagas.ListenerDashboard() {
            @Override
            public void success(List<Vaga> objeto) {
                adicionarItensLista(objeto);
                swipeRefreshLayout.setRefreshing(false);
                linearLayoutErro.setVisibility(View.INVISIBLE);
            }

            @Override
            public void error(String msmErro) {
                swipeRefreshLayout.setRefreshing(true);
                linearLayoutErro.setVisibility(View.VISIBLE);
            }
        });
    }

    private void adicionarItensLista(List<Vaga> objeto) {
        if(livres) {
            mAdapterVagas.addItens(getItensVagasLivres(objeto));
        } else {
            mAdapterVagas.addItens(objeto);
        }
    }

    private List<Vaga>  getItensVagasLivres(List<Vaga> objeto) {
        List<Vaga> livres = new ArrayList<>();
        for (Vaga vaga : objeto) {
            if(vaga.getSituacao()) {
                livres.add(vaga);
            }
        }
        return livres;
    }
}

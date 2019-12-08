package br.edu.ifsc.alunos.clientesd.ui.projetos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.RequestSituacao;
import br.edu.ifsc.alunos.clientesd.resources.projetos.ModuloConsultarProjetos;
import br.edu.ifsc.alunos.clientesd.ui.projetos.adapter.AdapterProjetos;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;

public class ProjetosActivity extends BaseActivty {

    private ModuloConsultarProjetos moduloConsultarProjetos;
    private RecyclerView mRecyclerView;
    private AdapterProjetos mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Projeto> mProjetos = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayoutErro;
    private SituacaoButtonSheetDialoig situacaoButtonSheetDialoig;
    private ImageView imageview;
    private AutoCompleteTextView autoCompleteTextView;
    private RequestSituacao requestSituacao;
    private List<String> anos;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_projetos);
        createRequest();
    }

    private void createRequest() {
        Calendar lCalendar = Calendar.getInstance();
        autoCompleteTextView.setText(String.valueOf(lCalendar.get(Calendar.YEAR)));
        requestSituacao = new RequestSituacao(lCalendar.get(Calendar.YEAR));
    }

    @Override
    protected void onResume() {
        super.onResume();
        consultWS();
    }

    private void consultWS() {
        swipeRefreshLayout.setRefreshing(true);
        moduloConsultarProjetos.consultar(requestSituacao);
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        setTitleToolbar("Projetos de pesquisa");
        configToolbar();
        linearLayoutErro = findViewById(R.id.container_msm_erro);
        mRecyclerView = findViewById(R.id.recycler_view);
        imageview = findViewById(R.id.img_status);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        autoCompleteTextView = findViewById(R.id.edt_txt_data_inicio);
        situacaoButtonSheetDialoig = SituacaoButtonSheetDialoig.newInstance();
        createAutoComplet();
        createList();
    }

    @Override
    public void mapActionComponents() {
        super.mapActionComponents();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moduloConsultarProjetos.consultar(new RequestSituacao());
            }
        });

        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    autoCompleteTextView.showDropDown();

            }
        });

//      Gohorde para transformar um edittext em um spinncer, pois o layout do spinner é esquisito.
        autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                if (anos.size() > 0) {
                    // show all suggestions
                    if (!autoCompleteTextView.getText().toString().equals(""))
                        adapter.getFilter().filter(null);
                    autoCompleteTextView.showDropDown();
                }
                return false;
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String ano = anos.get(position);
               if(ano.equalsIgnoreCase("Todos")) {
                   requestSituacao.setAno(null);
               } else {
                   requestSituacao.setAno(Integer.parseInt(ano));
               }
                consultWS();
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                situacaoButtonSheetDialoig.show(getSupportFragmentManager(), null);
            }
        });

        situacaoButtonSheetDialoig.setListener(new SituacaoButtonSheetDialoig.CallbackOptions() {
            @Override
            public void onClickListener(int situacao) {
                situacaoButtonSheetDialoig.dismiss();
                requestSituacao.setSituacao(situacao);
                if(situacao == -1) {
                    requestSituacao.setSituacao(null);
                }
                consultWS();
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
        moduloConsultarProjetos = new ModuloConsultarProjetos(new ModuloConsultarProjetos.ListenerDashboard() {
            @Override
            public void success(List<Projeto> objeto) {
                displayList(objeto);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(String msmErro) {
                swipeRefreshLayout.setRefreshing(false);
                mAdapter.clearItens();
                linearLayoutErro.setVisibility(View.VISIBLE);
            }
        });
    }


    public void createAutoComplet() {
        anos = new ArrayList<>();
        anos.add("Todos");
        anos.add("2017");
        anos.add("2018");
        anos.add("2019");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, anos);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setDropDownHeight(500);
    }

    public void createList() {
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterProjetos(ProjetosActivity.this, mProjetos);
        mRecyclerView.setAdapter(mAdapter);
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

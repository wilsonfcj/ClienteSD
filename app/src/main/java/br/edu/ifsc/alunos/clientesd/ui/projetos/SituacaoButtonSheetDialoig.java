package br.edu.ifsc.alunos.clientesd.ui.projetos;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.props.Situacao;
import br.edu.ifsc.alunos.clientesd.utilidades.MapElement;

public class SituacaoButtonSheetDialoig extends BottomSheetDialogFragment implements MapElement {

    private static final String TAG = "ScheduleOptionsFragment";

    private View mView;
    private CallbackOptions mCallbackOptions;
    private View mtvTodos;
    private View mtvAprovado;
    private View mtvSubmetido;
    private View mtvCadastrado;
    private View mtvFinalizado;
    private View mtvReprovado;
    private View mtvDesativado;
    private View mtvEmExecucao;
    private View mtvCadastroAndamento;
    private View mtvAvaliacao;
    private View mtvFinalizadoRenovado;
    private View mtvFinalizadoPendencias;

    private ProgressBar mPgMotivos;
    private TextView mTxtSemMotivos;
    private ViewSwitcher mViewSwitcher;

    public SituacaoButtonSheetDialoig() {/*Nothing*/}

    public static SituacaoButtonSheetDialoig newInstance() {
        SituacaoButtonSheetDialoig fragment = new SituacaoButtonSheetDialoig();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        restListeners();
    }

    private void restListeners() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context lContextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme);
        return inflater.cloneInContext(lContextThemeWrapper).inflate(R.layout.fragment_buttonsheet_situacao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mView = view;
        mapComponents();
        mapActionComponents();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void mapComponents() {
        mtvTodos = mView.findViewById(R.id.tv_todos);
        mtvAprovado = mView.findViewById(R.id.tv_aprovado);
        mtvSubmetido = mView.findViewById(R.id.tv_submetido);
        mtvCadastrado = mView.findViewById(R.id.tv_cadastrado);
        mtvFinalizado = mView.findViewById(R.id.tv_finalizado);
        mtvReprovado = mView.findViewById(R.id.tv_reprovado);
        mtvDesativado = mView.findViewById(R.id.tv_desativado);
        mtvEmExecucao = mView.findViewById(R.id.tv_emexecucao);
        mtvCadastroAndamento = mView.findViewById(R.id.tv_cadastro_em_andamento);
        mtvAvaliacao = mView.findViewById(R.id.tv_avaliacao_insuficiente);
        mtvFinalizadoRenovado = mView.findViewById(R.id.tv_finalizado_renovado);
        mtvFinalizadoPendencias = mView.findViewById(R.id.tv_finalizado_pendencias);
        mViewSwitcher = mView.findViewById(R.id.view_switcher);

        Animation lAnimationIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        lAnimationIn.setInterpolator(new OvershootInterpolator());
        mViewSwitcher.setInAnimation(lAnimationIn);

        Animation lAnimationOut = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
        lAnimationOut.setInterpolator(new AccelerateDecelerateInterpolator());
        mViewSwitcher.setOutAnimation(lAnimationOut);
    }

    @Override
    public void mapActionComponents() {
        mtvTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.DEFAULT.codigo);
            }
        });

        mtvAprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.APROVADO.codigo);
            }
        });

        mtvSubmetido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.SUBMETIDO.codigo);
            }
        });

        mtvCadastrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.CADASTRADO.codigo);
            }
        });

        mtvFinalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.FINALIZADO.codigo);
            }
        });

        mtvReprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.REPROVADO.codigo);
            }
        });

        mtvDesativado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.DESATIVADO.codigo);
            }
        });

        mtvEmExecucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.EM_EXECUCAO.codigo);
            }
        });

        mtvCadastroAndamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.CADSTRO_EM_ANDAMENTO.codigo);
            }
        });

        mtvAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.AVALIAÇAO_INSUFICIENTE.codigo);
            }
        });

        mtvFinalizadoRenovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.FINALIZADO_RENOVADO.codigo);
            }
        });

        mtvFinalizadoPendencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOptions.onClickListener(Situacao.FINALIZADO_COM_PENDENCIAS.codigo);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getDialog().getWindow().getAttributes().windowAnimations = R.style.BottoomSheetDialogAnimation;
        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public void setListener(CallbackOptions aListener) {
        mCallbackOptions = aListener;
    }

    public abstract static class CallbackOptions {
        public abstract void onClickListener(int situacao);
    }
}

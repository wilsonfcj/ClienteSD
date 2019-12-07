//package br.edu.ifsc.alunos.clientesd.ui.projetos;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.ContextThemeWrapper;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.animation.AccelerateDecelerateInterpolator;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.OvershootInterpolator;
//import android.widget.TextView;
//import android.widget.ViewSwitcher;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//import java.util.Calendar;
//
//import br.edu.ifsc.alunos.clientesd.R;
//
//public class FilterYearButtonSheetDialoig extends BottomSheetDialogFragment {
//
//    private static final String TAG = "ScheduleOptionsFragment";
//
//    private View mView;
//    private CallbackOptions mCallbackOptions;
//    private TextView mTxtVwAccept;
//    private TextView mTxtVwRefuse;
//    private TextView mTxtVwTitle;
//    private TextView mTxtVwText;
//    private ViewSwitcher mViewSwitcher;
//
//    public FilterYearButtonSheetDialoig() {/*Nothing*/}
//
//    public static FilterYearButtonSheetDialoig newInstance() {
//        Bundle args = new Bundle();
//        FilterYearButtonSheetDialoig fragment = new FilterYearButtonSheetDialoig();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setCancelable(true);
//        restListeners();
//    }
//
//    private void restListeners() {
//
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Context lContextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme);
//        return inflater.cloneInContext(lContextThemeWrapper).inflate(R.layout.fragment_flash_bar_sync, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        mView = view;
//        mapViews();
//        actionsViews();
//        if (getArguments() != null) {
////            Adicção de texto ou informações nos componentes.
////            Integer lStatus = getArguments().getInt(CheckListProps.Agendamento.Bundle.STATUS);
////            Long lIdAgendamento = getArguments().getLong(CheckListProps.Agendamento.Bundle.ID_AGENDAMENTO, -1);
//        }
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    private void mapViews() {
//        mTxtVwAccept = mView.findViewById(R.id.buttonYes);
//        mTxtVwRefuse = mView.findViewById(R.id.buttonNo);
//        mViewSwitcher = mView.findViewById(R.id.view_switcher);
//
//        Animation lAnimationIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
//        lAnimationIn.setInterpolator(new OvershootInterpolator());
//        mViewSwitcher.setInAnimation(lAnimationIn);
//
//        Animation lAnimationOut = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
//        lAnimationOut.setInterpolator(new AccelerateDecelerateInterpolator());
//        mViewSwitcher.setOutAnimation(lAnimationOut);
//    }
//
//    private void actionsViews() {
//        mTxtVwAccept.setOnClickListener(aView -> mCallbackOptions.onYes());
//        mTxtVwRefuse.setOnClickListener(aView -> mCallbackOptions.onNo());
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        getDialog().getWindow().getAttributes().windowAnimations = R.style.BottoomSheetDialogAnimation;
//        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    }
//
//    public void setListener(CallbackOptions aListener) {
//        mCallbackOptions = aListener;
//    }
//
//    public abstract static class CallbackOptions {
//        public abstract void onYes();
//        public abstract void onNo();
//    }
//
//
//}

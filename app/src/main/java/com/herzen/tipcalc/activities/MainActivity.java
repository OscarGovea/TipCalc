package com.herzen.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.herzen.tipcalc.R;
import com.herzen.tipcalc.TipCalcApp;
import com.herzen.tipcalc.fragments.TipHistoryListFragment;
import com.herzen.tipcalc.fragments.TipHistoryListFragmentListener;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_CHANGE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_about){
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleSubmit() {
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();

        if(!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPrecentage();

            double tip = total * (tipPercentage/100d);

            String strTip = String.format(getString(R.string.global_message_tip), tip);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease() {
        // Cuando des click a + debe llamar a handleTipChange y sumar 1
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease() {
        // Cuando des click a - debe llamar a handleTipChange y restar 1
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }


    public int getTipPrecentage() {
        // 1 Crear una variable tipPercentage en la que guardemos DEFAULT_TIP_CHANGE
        int tipPercentage = DEFAULT_TIP_CHANGE;
        // 2 Crear una variable String strInputTipPercentage que tome el valor del inputPercentage (No olviden el trim)
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        // 3 Verificar que la cadena no venga vacia
        if(strInputTipPercentage.isEmpty()){
            // 3a Si no Viene vacia sobreEscribir tipPercentage con el valor de strInputTipPercentage (No olviden convertirlo a entero)
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else{
            // 3b inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTAGE));
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_CHANGE));
        }
        // 4 Devolver el valor de tipPercentage
        return tipPercentage;


    }

    public void handleTipChange(int change) {
        // 1 Llamar a Get Tip Percentage (en una variable)
        int tipPercentage = getTipPrecentage();
        // 2 aplicar el incremento/decremento que viene en la variable change
        tipPercentage += change;
        // 3 si tipPercentage mayor que 0 entonces colocar el valor del incremento en el input de la vista
        if(tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }

    }

    private void hideKeyboard() {
        InputMethodManager inputManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }


    private void about() {
        TipCalcApp app= (TipCalcApp)getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
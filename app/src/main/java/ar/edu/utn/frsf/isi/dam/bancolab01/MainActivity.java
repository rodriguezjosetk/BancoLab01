package ar.edu.utn.frsf.isi.dam.bancolab01;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import modelo.Cliente;
import modelo.Moneda;
import modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView lblDiasDePlazo;
    private PlazoFijo pf;
    private Cliente cliente;
    private TextInputEditText txtCorreo;
    private TextInputEditText txtCuit;
    private RadioButton rbDolar;
    private RadioButton rbPeso;
    private TextInputEditText txtMonto;
    private Switch swMailVencimiento;
    private ToggleButton toggle;
    private CheckBox chxTerminos;
    private Button btnHacerPlazoFijo;
    private TextView lblInformacionControl;
    private TextView lblIntereses;




    //METODO ON CREATE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VISTAS INDIVIDUALES
        txtCorreo = (TextInputEditText) findViewById(R.id.lblMail);
        txtCuit = (TextInputEditText) findViewById(R.id.lblCuit);
        rbDolar = (RadioButton) findViewById(R.id.rbDolar);
        rbPeso = (RadioButton) findViewById(R.id.rbPeso);
        txtMonto = (TextInputEditText) findViewById(R.id.lblMonto);
        swMailVencimiento = (Switch) findViewById(R.id.switchVencimiento);
        toggle = (ToggleButton) findViewById(R.id.toggleOption);
        chxTerminos = (CheckBox) findViewById(R.id.chxTerminosyCondiciones);
        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPlazoFijo);
        lblInformacionControl = (TextView) findViewById(R.id.lblInformacionControl);
        seekBar = (SeekBar) findViewById(R.id.seekBarDias);
        lblDiasDePlazo = (TextView) findViewById((R.id.lblDiasDePlazo));
        lblIntereses = (TextView) findViewById(R.id.lblIntereses);


        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas)); //SETEO DE LA INSTANCIA DE PLAZO FIJO
        btnHacerPlazoFijo.setEnabled(false);
        seekBar.setMax(170);//se fija en 170 y no 180 porque el minimo se controla desde el listener, ya que no quiero hacer dos main activities para diferentes apis
        rbPeso.setChecked(true);



        //LISTENER SOBRE EL CAMPO DE MONTO PARA QUE SE CONFIGURE EL MONTO EN LA INSTANCIA DE PLAZO FIJO CUANDO SE INGRESA O CAMBIA EL MONTO EN EL EDITTEXT
        txtMonto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!= 0) {
                    pf.setMonto(Double.parseDouble(s.toString()));
                    lblIntereses.setText("Intereses:  $" + Double.toString(pf.intereses()));

                }
                else{
                    pf.setMonto(0.0);
                    lblIntereses.setText("Intereses:  $" + Double.toString(pf.intereses()));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //COMPORTAMIENTO DEL SEEKBAR
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //SETEO DEL LABEL DE DIAS EN PANTALLA
                lblDiasDePlazo.setText(progress+10 + " dias de plazo");
                //SETEO DE DIAS EN PLAZO FIJO
                pf.setDias(progress+10);
                //CALCULO DE INTERESES Y SETEO EN EL LBL
                lblIntereses.setText("Intereses:  $" + Double.toString(pf.intereses()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //LISTENER SOBRE EL CHECHBOX DE TERMINOS Y CONDICIONES
        chxTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnHacerPlazoFijo.setEnabled(isChecked);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "“Es obligatorio aceptar las\n" +
                            "condiciones";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                    btnHacerPlazoFijo.setEnabled(isChecked);
                }

            }
        });



        //LISTENER TOGGLE
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pf.setRenovarAutomaticamente(isChecked);
            }
        });



        //LISTENER CAMPO CUIT
        txtCuit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    pf.getCliente().setCuil(s.toString());
                }
                else{
                    pf.getCliente().setCuil("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //LISTENER CORREO ELECTRONICO
        txtCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    pf.getCliente().setMail(s.toString());
                }
                else{
                    pf.getCliente().setMail("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //LISTENER RADIOBUTTON DOLARES
        rbDolar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pf.setMoneda(Moneda.DOLAR);
                }
            }
        });



        //LISTENER RADIOBUTTON PESO
        rbPeso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pf.setMoneda(Moneda.PESO);
                }
            }
        });



        //LISTENER SWITCH
        swMailVencimiento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pf.setAvisarVencimiento(isChecked);
                }
            }
        });

    }


    public void onClick (View view){

//        if(pf.getCliente().getMail().length() != 0){
//            if(pf.getCliente().getCuil().length() != 0){
//                if(pf.getMonto() > 0.0){
//                    lblInformacionControl.setTextColor(getResources().getColor(R.color.colorAccent));
//                    lblInformacionControl.setText(pf.toString());
//                }
//                else{
//                    Toast toast = Toast.makeText(getApplicationContext(), "Ingrese un monto válido", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER, 0,0);
//                    toast.show();
//                    lblInformacionControl.setText("");
//                }
//            }
//            else{
//                Toast toast = Toast.makeText(getApplicationContext(), "Ingrese un CUIT/CUIL", Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER, 0,0);
//                toast.show();
//                lblInformacionControl.setText("");
//            }
//        }
//        else{
//            Toast toast = Toast.makeText(getApplicationContext(), "Ingrese un correo electrónico", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0,0);
//            toast.show();
//            lblInformacionControl.setText("");
//        }
    }
}

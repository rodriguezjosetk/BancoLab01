package ar.edu.utn.frsf.isi.dam.bancolab01;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import modelo.Cliente;
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




    //METODO ON CREATE

    @RequiresApi(api = Build.VERSION_CODES.O)
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


        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas)); //SETEO DE LA INSTANCIA DE PLAZO FIJO
        btnHacerPlazoFijo.setEnabled(false);
        seekBar.setMax(170);//se fija en 170 y no 180 porque el minimo se controla desde el listener, ya que no quiero hacer dos main activities para diferentes apis



        //COMPORTAMIENTO DEL SEEKBAR

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lblDiasDePlazo.setText(progress+10 + " dias de plazo");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }






}

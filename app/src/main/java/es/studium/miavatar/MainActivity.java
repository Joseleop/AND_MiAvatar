package es.studium.miavatar;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements OnDialogoListener {

    Button btnNombre;
    Button btnSexo;
    Button btnRaza;
    Button btnProfesion;

    private String nombre = null;
    private int sexo = DialogoSexo.CHECK_SEXO_SIN_ELEGIR;
    private int raza = DialogoRaza.CHECK_RAZA_SIN_ELEGIR;

    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String TAG_DIALOGO_SEXO = "dialogoSexo";
    private static final String TAG_DIALOGO_RAZA = "dialogoRaza";

    //private static final String KEY_NOMBRE_BUNDLE = "nombre";
    //private static final String KEY_SEXO_BUNDLE = "sexo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNombre = findViewById(R.id.btnNombre);
        btnSexo = findViewById(R.id.btnSexo);
        btnRaza = findViewById(R.id.btnRaza);


        btnNombre.setOnClickListener(v -> {
            DialogoNombre dialogoNombre = new DialogoNombre();
            dialogoNombre.setCancelable(false);
            crearBundle(dialogoNombre);
            dialogoNombre.show(getSupportFragmentManager(), TAG_DIALOGO_NOMBRE);
        });

        btnSexo.setOnClickListener(v -> {
            DialogoSexo dialogoSexo = new DialogoSexo();
            dialogoSexo.setCancelable(false);
            crearBundle(dialogoSexo);
            dialogoSexo.show(getSupportFragmentManager(), TAG_DIALOGO_SEXO);
        });

        btnRaza.setOnClickListener(v -> {
            DialogoRaza dialogoRaza = new DialogoRaza();
            dialogoRaza.setCancelable(false);
            crearBundle(dialogoRaza);
            dialogoRaza.show(getSupportFragmentManager(), TAG_DIALOGO_RAZA);
        });

        if (savedInstanceState != null) {
            nombre = savedInstanceState.getString(DialogoNombre.KEY_NOMBRE_BUNDLE);
            sexo = savedInstanceState.getInt(DialogoSexo.KEY_SEXO_BUNDLE, DialogoSexo.CHECK_SEXO_SIN_ELEGIR);
            raza = savedInstanceState.getInt(DialogoRaza.KEY_RAZA_BUNDLE, DialogoRaza.CHECK_RAZA_SIN_ELEGIR);
            ActualizarBotones.actualizarBotonNombre(btnNombre, nombre);
            ActualizarBotones.actualizarBotonSexo(btnSexo, sexo);
            ActualizarBotones.actualizarBotonRaza(btnRaza, raza);
        }
    }

    private void crearBundle(DialogFragment dialogoAbierto) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, this.nombre);
        bundle.putInt(DialogoSexo.KEY_SEXO_BUNDLE, this.sexo);
        bundle.putInt(DialogoRaza.KEY_RAZA_BUNDLE, this.raza);
        dialogoAbierto.setArguments(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, nombre);
        outState.putInt(DialogoSexo.KEY_SEXO_BUNDLE, sexo);
        outState.putInt(DialogoRaza.KEY_RAZA_BUNDLE, raza);
    }

    @Override
    public void onDialogoNombreGuardarListener(String nombre) {
        if (nombre.isEmpty()) {
            this.nombre = null;
        } else {
            this.nombre = nombre;
        }
        ActualizarBotones.actualizarBotonNombre(btnNombre, this.nombre);
    }

    @Override
    public void onDialogoSexoGuardarListener(int sexoElegido) {
        if(sexoElegido == DialogoSexo.CHECK_SEXO_SIN_ELEGIR) {
            return;
        }
        this.sexo = sexoElegido;
        ActualizarBotones.actualizarBotonSexo(btnSexo, this.sexo);
    }

    @Override
    public void onDialogoRazaGuardarListener(int razaElegida) {
        if(razaElegida == DialogoRaza.CHECK_RAZA_SIN_ELEGIR){
            return;
        }
        this.raza = razaElegida;
        ActualizarBotones.actualizarBotonRaza(btnRaza, this.raza);
    }

    @Override
    public void onDialogoNombreCancelarListener() {
    }
    @Override
    public void onDialogoSexoCancelarListener() {
    }
    @Override
    public void onDialogoRazaCancelarListener() {

    }

}
package es.studium.miavatar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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
    private int sexo = 0;

    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String TAG_DIALOGO_SEXO = "dialogoSexo";

    private static final String KEY_NOMBRE_BUNDLE = "nombre";
    private static final String KEY_SEXO_BUNDLE = "sexo";

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

        if (savedInstanceState != null) {
            nombre = savedInstanceState.getString(KEY_NOMBRE_BUNDLE);
            sexo = savedInstanceState.getInt(KEY_SEXO_BUNDLE);
            ActualizarBotones.actualizarBotonNombre(btnNombre, nombre);
            ActualizarBotones.actualizarBotonSexo(btnSexo, sexo);
        }
    }

    private void crearBundle(DialogFragment dialogoAbierto) {
        Bundle bundle = new Bundle();
        bundle.putString("nombre", this.nombre);
        bundle.putInt("sexo", this.sexo);
        dialogoAbierto.setArguments(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NOMBRE_BUNDLE, nombre);
        outState.putInt(KEY_SEXO_BUNDLE, sexo);
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
    public void onDialogoNombreCancelarListener() {
    }

    @Override
    public void onDialogoSexoGuardarListener(int sexoElegido) {
        if(sexoElegido == -1) {
            return;
        }
        this.sexo = sexoElegido;
        ActualizarBotones.actualizarBotonSexo(btnSexo, this.sexo);
    }

    @Override
    public void onDialogoSexoCancelarListener() {
    }

}
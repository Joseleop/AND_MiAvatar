package es.studium.miavatar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements OnDialogoListener{

    Button btnNombre;
    Button btnSexo;
    Button btnRaza;
    Button btnProfesion;

    DialogoNombre dialogoNombre;

    private String nombre = null;

    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String KEY_NOMBRE_BUNDLE = "nombre";

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
        if(savedInstanceState != null){
            nombre = savedInstanceState.getString(KEY_NOMBRE_BUNDLE);
            ActualizarBotones.actualizarBotonNombre(btnNombre, nombre);
        }
        btnNombre.setOnClickListener(v -> {
            dialogoNombre = new DialogoNombre();
            dialogoNombre.setCancelable(false);
            crearBundle();
            dialogoNombre.show(getSupportFragmentManager(), TAG_DIALOGO_NOMBRE);
        });

        btnSexo = findViewById(R.id.btnSexo);

    }

    private void crearBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("nombre",this.nombre);
        dialogoNombre.setArguments(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NOMBRE_BUNDLE, nombre);
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
    public void onDialogoNombreCancelarListener() {}

    @Override
    public void onDialogoSexoGuardarListener() {

    }

    @Override
    public void onDialogoSexoCancelarListener() {}

}
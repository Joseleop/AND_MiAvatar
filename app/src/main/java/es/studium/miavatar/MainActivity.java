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

    private final Personaje personaje = new Personaje();

    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String TAG_DIALOGO_SEXO = "dialogoSexo";
    private static final String TAG_DIALOGO_RAZA = "dialogoRaza";
    private static final String TAG_DIALOGO_PROFESION = "dialogoProfesion";

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
        btnProfesion = findViewById(R.id.btnProfesion);


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

        btnProfesion.setOnClickListener(v -> {
            DialogoProfesion dialogoProfesion = new DialogoProfesion();
            dialogoProfesion.setCancelable(false);
            crearBundle(dialogoProfesion);
            dialogoProfesion.show(getSupportFragmentManager(), TAG_DIALOGO_PROFESION);
        });

        if (savedInstanceState != null) {
            personaje.setNombre(savedInstanceState.getString(DialogoNombre.KEY_NOMBRE_BUNDLE));
            personaje.setSexo(savedInstanceState.getInt(DialogoSexo.KEY_SEXO_BUNDLE, Personaje.CHECK_SEXO_SIN_ELEGIR));
            personaje.setRaza(savedInstanceState.getInt(DialogoRaza.KEY_RAZA_BUNDLE, Personaje.CHECK_RAZA_SIN_ELEGIR));
            personaje.setProfesion(savedInstanceState.getInt(DialogoProfesion.KEY_PROFESION_BUNDLE, Personaje.CHECK_PROFESION_SIN_ELEGIR));
            ActualizarBotones.actualizarBotonNombre(btnNombre, personaje.getNombre());
            ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
            ActualizarBotones.actualizarBotonRaza(btnRaza, personaje.getRaza());
            ActualizarBotones.actualizarBotonProfesion(btnProfesion, personaje.getProfesion());
        }
    }
    //Este bundle es el que sirve para llevar los datos si están rellenos al diálogo
    private void crearBundle(DialogFragment dialogoAbierto) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, personaje.getNombre());
        bundle.putInt(DialogoSexo.KEY_SEXO_BUNDLE, personaje.getSexo());
        bundle.putInt(DialogoRaza.KEY_RAZA_BUNDLE, personaje.getRaza());
        bundle.putInt(DialogoProfesion.KEY_PROFESION_BUNDLE, personaje.getProfesion());
        dialogoAbierto.setArguments(bundle);
    }

    //Con este bundle se quedan los campos tras girar el movil
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, personaje.getNombre());
        outState.putInt(DialogoSexo.KEY_SEXO_BUNDLE, personaje.getSexo());
        outState.putInt(DialogoRaza.KEY_RAZA_BUNDLE, personaje.getRaza());
        outState.putInt(DialogoProfesion.KEY_PROFESION_BUNDLE, personaje.getProfesion());
    }

    @Override
    public void onDialogoNombreGuardarListener(String nombre) {
        if (nombre.isEmpty()) {
            personaje.setNombre(null);
        } else {
            personaje.setNombre(nombre);
        }
        ActualizarBotones.actualizarBotonNombre(btnNombre, personaje.getNombre());
    }

    @Override
    public void onDialogoSexoGuardarListener(int sexoElegido) {
        if (sexoElegido == Personaje.CHECK_SEXO_SIN_ELEGIR) {
            return;
        }
        personaje.setSexo(sexoElegido);
        ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
    }

    @Override
    public void onDialogoRazaGuardarListener(int razaElegida) {
        if (razaElegida == Personaje.CHECK_RAZA_SIN_ELEGIR) {
            return;
        }
        personaje.setRaza(razaElegida);
        ActualizarBotones.actualizarBotonRaza(btnRaza, personaje.getRaza());
    }

    @Override
    public void onDialogoProfesionGuardarListener(int profesionElegida) {
        if (profesionElegida == Personaje.CHECK_PROFESION_SIN_ELEGIR) {
            return;
        }
        personaje.setProfesion(profesionElegida);
        ActualizarBotones.actualizarBotonProfesion(btnProfesion, personaje.getProfesion());
    }


    @Override
    public void onDialogoNombreCancelarListener() {}
    @Override
    public void onDialogoSexoCancelarListener() {}
    @Override
    public void onDialogoRazaCancelarListener() {}
    @Override
    public void onDialogoProfesionCancelarListener() {}

}
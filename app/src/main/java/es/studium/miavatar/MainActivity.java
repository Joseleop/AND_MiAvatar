package es.studium.miavatar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    View layoutFicha;
    Button btnReset;

    TextView tvNombreFicha;
    TextView tvRazaProfesion;
    TextView tvVida;
    TextView tvMagia;
    TextView tvFuerza;
    TextView tvVelocidad;

    ImageView ivAvatar;

    private final Personaje personaje = new Personaje();

    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String TAG_DIALOGO_SEXO = "dialogoSexo";
    private static final String TAG_DIALOGO_RAZA = "dialogoRaza";
    private static final String TAG_DIALOGO_PROFESION = "dialogoProfesion";
    private static final String TAG_DIALOGO_REINICIAR = "dialogoReiniciar";

    private static final String KEY_VIDA_BUNDLE = "vida";
    private static final String KEY_MAGIA_BUNDLE = "magia";
    private static final String KEY_FUERZA_BUNDLE = "fuerza";
    private static final String KEY_VELOCIDAD_BUNDLE = "velocidad";
    private static final String KEY_NOMBRE_BUNDLE = "nombre";
    private static final String KEY_SEXO_BUNDLE = "sexo";
    private static final String KEY_RAZA_BUNDLE = "raza";
    private static final String KEY_PROFESION_BUNDLE = "profesion";

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

        layoutFicha = findViewById(R.id.layout_second);
        btnReset = findViewById(R.id.btnReset);

        tvNombreFicha = findViewById(R.id.tvNombreFicha);
        tvRazaProfesion = findViewById(R.id.tvRazaProfesion);
        tvVida = findViewById(R.id.tvVida);
        tvMagia = findViewById(R.id.tvMagia);
        tvFuerza = findViewById(R.id.tvFuerza);
        tvVelocidad = findViewById(R.id.tvVelocidad);

        ivAvatar = findViewById(R.id.ivAvatar);

        btnNombre.setOnClickListener(v -> {
            DialogoNombre dialogoNombre = new DialogoNombre();
            abrirDialogo(dialogoNombre, TAG_DIALOGO_NOMBRE);
        });

        btnSexo.setOnClickListener(v -> {
            DialogoSexo dialogoSexo = new DialogoSexo();
            abrirDialogo(dialogoSexo, TAG_DIALOGO_SEXO);
        });

        btnRaza.setOnClickListener(v -> {
            DialogoRaza dialogoRaza = new DialogoRaza();
            abrirDialogo(dialogoRaza, TAG_DIALOGO_RAZA);
        });

        btnProfesion.setOnClickListener(v -> {
            DialogoProfesion dialogoProfesion = new DialogoProfesion();
            abrirDialogo(dialogoProfesion, TAG_DIALOGO_PROFESION);
        });

        btnReset.setOnClickListener(v -> {
            DialogoReiniciar dialogoReiniciar = new DialogoReiniciar();
            abrirDialogo(dialogoReiniciar, TAG_DIALOGO_REINICIAR);
        });

        if (savedInstanceState != null) {
            restaurarEstado(savedInstanceState);
            repintarBotones();
            actualizarFichaPersonaje();
        }
    }

    private void abrirDialogo(DialogFragment dialog, String tag) {
        dialog.setCancelable(false);
        enviarArgumentosAlDialogo(dialog);
        dialog.show(getSupportFragmentManager(), tag);
    }

    private void repintarBotones() {
        ActualizarBotones.actualizarBotonNombre(btnNombre, personaje.getNombre());
        ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
        ActualizarBotones.actualizarBotonRaza(btnRaza, personaje.getRaza());
        ActualizarBotones.actualizarBotonProfesion(btnProfesion, personaje.getProfesion());
    }

    //Con esto restauramos los datos de cada botón (y del personaje) para girar el móvil
    private void restaurarEstado(Bundle savedInstanceState) {
        personaje.setNombre(savedInstanceState.getString(KEY_NOMBRE_BUNDLE));
        personaje.setSexo(savedInstanceState.getInt(KEY_SEXO_BUNDLE, Personaje.CHECK_SEXO_SIN_ELEGIR));
        personaje.setRaza(savedInstanceState.getInt(KEY_RAZA_BUNDLE, Personaje.CHECK_RAZA_SIN_ELEGIR));
        personaje.setProfesion(savedInstanceState.getInt(KEY_PROFESION_BUNDLE, Personaje.CHECK_PROFESION_SIN_ELEGIR));
        int vidaBundle = savedInstanceState.getInt(KEY_VIDA_BUNDLE, Personaje.STAT_SIN_GENERAR);
        int magiaBundle = savedInstanceState.getInt(KEY_MAGIA_BUNDLE, Personaje.STAT_SIN_GENERAR);
        int fuerzaBundle = savedInstanceState.getInt(KEY_FUERZA_BUNDLE, Personaje.STAT_SIN_GENERAR);
        int velocidadBundle = savedInstanceState.getInt(KEY_VELOCIDAD_BUNDLE, Personaje.STAT_SIN_GENERAR);
        personaje.setPoderes(vidaBundle, magiaBundle, fuerzaBundle, velocidadBundle);
    }

    private void guardarEstado(Bundle outState) {
        outState.putString(KEY_NOMBRE_BUNDLE, personaje.getNombre());
        outState.putInt(KEY_SEXO_BUNDLE, personaje.getSexo());
        outState.putInt(KEY_RAZA_BUNDLE, personaje.getRaza());
        outState.putInt(KEY_PROFESION_BUNDLE, personaje.getProfesion());

        outState.putInt(KEY_VIDA_BUNDLE, personaje.getVida());
        outState.putInt(KEY_MAGIA_BUNDLE, personaje.getMagia());
        outState.putInt(KEY_FUERZA_BUNDLE, personaje.getFuerza());
        outState.putInt(KEY_VELOCIDAD_BUNDLE, personaje.getVelocidad());
    }

    //Este bundle es el que sirve para llevar los datos si están rellenos al diálogo
    private void enviarArgumentosAlDialogo(DialogFragment dialogoAbierto) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, personaje.getNombre());
        bundle.putInt(DialogoSexo.KEY_SEXO_BUNDLE, personaje.getSexo());
        bundle.putInt(DialogoRaza.KEY_RAZA_BUNDLE, personaje.getRaza());
        bundle.putInt(DialogoProfesion.KEY_PROFESION_BUNDLE, personaje.getProfesion());
        dialogoAbierto.setArguments(bundle);
    }

    private void actualizarFichaPersonaje() {
        if (personaje.estaCompleto()) {
            if (!personaje.tienePoderes()) {
                GeneradorPoderes.generarPoderes(personaje);
            }
            pintarDatosEnFicha();
            pintarAvatarEnFicha();
            mostrarFicha();
        } else{
            layoutFicha.setVisibility(View.GONE);
        }
    }

    private void pintarDatosEnFicha() {
        tvNombreFicha.setText(personaje.getNombre());
        tvRazaProfesion.setText(getString(
                R.string.ficha_raza_profesion,
                getString(ActualizarBotones.textoRaza(personaje.getRaza())),
                getString(ActualizarBotones.textoProfesion(personaje.getProfesion()))));

        tvVida.setText(getString(R.string.texto_stats_vida, personaje.getVida()));
        tvMagia.setText(getString(R.string.texto_stats_magia, personaje.getMagia()));
        tvFuerza.setText(getString(R.string.texto_stats_fuerza, personaje.getFuerza()));
        tvVelocidad.setText(getString(R.string.texto_stats_velocidad, personaje.getVelocidad()));
    }

    private void pintarAvatarEnFicha() {
        String nombreAvatar = GeneradorNombreAvatar.nombreImagen(personaje);
        int idImagen = getResources().getIdentifier(nombreAvatar, "drawable", getPackageName());
        if (idImagen == 0) {
            idImagen = R.drawable.avatar_desconocido;
        }
        ivAvatar.setImageResource(idImagen);
    }

    private void mostrarFicha(){
        boolean estaOculta = layoutFicha.getVisibility() != View.VISIBLE;
        if (estaOculta) {
            layoutFicha.setAlpha(0f);
            layoutFicha.setVisibility(View.VISIBLE);
            layoutFicha.animate().alpha(1f).setDuration(400);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        guardarEstado(outState);
    }

    @Override
    public void onDialogoNombreGuardarListener(String nombre) {
        if (nombre.isEmpty()) {
            personaje.setNombre(null);
        } else {
            personaje.setNombre(nombre);
        }
        ActualizarBotones.actualizarBotonNombre(btnNombre, personaje.getNombre());
        actualizarFichaPersonaje();
    }

    @Override
    public void onDialogoSexoGuardarListener(int sexoElegido) {
        if (sexoElegido == Personaje.CHECK_SEXO_SIN_ELEGIR) {
            return;
        }
        personaje.setSexo(sexoElegido);
        ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
        actualizarFichaPersonaje();
    }

    @Override
    public void onDialogoRazaGuardarListener(int razaElegida) {
        if (razaElegida == Personaje.CHECK_RAZA_SIN_ELEGIR) {
            return;
        }
        if (razaElegida != personaje.getRaza()) {
            personaje.setRaza(razaElegida);
            personaje.borrarPoderes();
        }
        ActualizarBotones.actualizarBotonRaza(btnRaza, personaje.getRaza());
        actualizarFichaPersonaje();
    }

    @Override
    public void onDialogoProfesionGuardarListener(int profesionElegida) {
        if (profesionElegida == Personaje.CHECK_PROFESION_SIN_ELEGIR) {
            return;
        }

        if (profesionElegida != personaje.getProfesion()) {
            personaje.setProfesion(profesionElegida);
            personaje.borrarPoderes();
        }
        ActualizarBotones.actualizarBotonProfesion(btnProfesion, personaje.getProfesion());
        actualizarFichaPersonaje();
    }


    @Override
    public void onDialogoReiniciarAceptarListener() {
        personaje.reiniciar();
        repintarBotones();
        actualizarFichaPersonaje();
    }


    @Override
    public void onDialogoNombreCancelarListener() {}

    @Override
    public void onDialogoSexoCancelarListener() {}

    @Override
    public void onDialogoRazaCancelarListener() {}

    @Override
    public void onDialogoProfesionCancelarListener() {}

    @Override
    public void onDialogoReiniciarCancelarListener() {}

}
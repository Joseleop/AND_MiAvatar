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

/**
 * Pantalla única de la app. Tiene 4 botones (nombre, sexo, raza, profesión) que abren
 * un diálogo cada uno; cuando el personaje está completo muestra la ficha con avatar y
 * poderes. Implementa OnDialogoListener para recibir lo que decide cada diálogo.
 *
 * Orden del archivo: constantes y datos -> ciclo de vida -> estado del giro -> diálogos
 * -> ficha y botones -> acciones sobre el personaje -> listeners de los diálogos.
 */
public class MainActivity extends AppCompatActivity implements OnDialogoListener {

    // Etiquetas con las que el FragmentManager identifica cada diálogo abierto.
    private static final String TAG_DIALOGO_NOMBRE = "dialogoNombre";
    private static final String TAG_DIALOGO_SEXO = "dialogoSexo";
    private static final String TAG_DIALOGO_RAZA = "dialogoRaza";
    private static final String TAG_DIALOGO_PROFESION = "dialogoProfesion";
    private static final String TAG_DIALOGO_REINICIAR = "dialogoReiniciar";
    private static final String TAG_DIALOGO_ADVERTENCIA = "dialogoAdvertencia";

    // Claves del Bundle del GIRO (el que crea Android en onSaveInstanceState).
    // Sin nombre de clase delante: pertenecen a MainActivity. Las del diálogo llevan
    // delante la clase del diálogo (p. ej. DialogoNombre.KEY_NOMBRE_BUNDLE).
    private static final String KEY_VIDA_BUNDLE = "vida";
    private static final String KEY_MAGIA_BUNDLE = "magia";
    private static final String KEY_FUERZA_BUNDLE = "fuerza";
    private static final String KEY_VELOCIDAD_BUNDLE = "velocidad";
    private static final String KEY_NOMBRE_BUNDLE = "nombre";
    private static final String KEY_SEXO_BUNDLE = "sexo";
    private static final String KEY_RAZA_BUNDLE = "raza";
    private static final String KEY_PROFESION_BUNDLE = "profesion";

    // Botones de datos y vistas de la ficha (se enlazan en onCreate).
    private Button btnNombre;
    private Button btnSexo;
    private Button btnRaza;
    private Button btnProfesion;

    private View layoutFicha;
    private Button btnReset;
    private Button btnGenerar;

    private TextView tvNombreFicha;
    private TextView tvRazaProfesion;
    private TextView tvVida;
    private TextView tvMagia;
    private TextView tvFuerza;
    private TextView tvVelocidad;

    private ImageView ivAvatar;

    // Datos del personaje: única fuente de la verdad; botones y ficha solo lo pintan.
    private final Personaje personaje = new Personaje();

    // ==========================================================================
    // CICLO DE VIDA
    // ==========================================================================

    // Enlaza vistas, configura los botones y, si venimos de un giro, restaura el personaje.
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
        btnGenerar = findViewById(R.id.btnGenerar);

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

        btnGenerar.setOnClickListener(v -> {
            pedirConfirmacionCambio(DialogoAdvertencia.GENERAR_PODERES, 0, TAG_DIALOGO_ADVERTENCIA);
        });

        if (savedInstanceState != null) {
            restaurarEstado(savedInstanceState);
            repintarBotones();
            actualizarFichaPersonaje();
        }
    }

    // Android llama a este método antes de destruir la Activity (por ejemplo al girar).
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        guardarEstado(outState);
    }

    // ==========================================================================
    // ESTADO DEL GIRO (Bundle que crea Android, no yo)
    // ==========================================================================

    // Ruta: personaje -> outState (lo rellena guardarEstado desde onSaveInstanceState).
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

    // Ruta: savedInstanceState (llega a onCreate; es null la primera vez) -> personaje.
    // Después de llamarlo hay que repintar botones y ficha, que no se guardan solos.
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

    // ==========================================================================
    // DIÁLOGOS
    // ==========================================================================

    // Abre un diálogo de datos precargándolo con el personaje. Si ya hay uno abierto con
    // esa etiqueta (doble toque rápido) no abre otro.
    private void abrirDialogo(DialogFragment dialog, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            return;
        }
        dialog.setCancelable(false);
        enviarArgumentosAlDialogo(dialog);
        dialog.show(getSupportFragmentManager(), tag);
    }

    // Bundle del DIÁLOGO: lo creo yo con new Bundle() y se lee con getArguments() dentro del
    // diálogo. Sirve para que cada diálogo aparezca con lo ya elegido (precarga).
    private void enviarArgumentosAlDialogo(DialogFragment dialogoAbierto) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogoNombre.KEY_NOMBRE_BUNDLE, personaje.getNombre());
        bundle.putInt(DialogoSexo.KEY_SEXO_BUNDLE, personaje.getSexo());
        bundle.putInt(DialogoRaza.KEY_RAZA_BUNDLE, personaje.getRaza());
        bundle.putInt(DialogoProfesion.KEY_PROFESION_BUNDLE, personaje.getProfesion());
        dialogoAbierto.setArguments(bundle);
    }

    // Diálogo de advertencia: se le pasa QUÉ acción pide confirmar (tipo) y el valor elegido;
    // al aceptar, el diálogo los devuelve y onDialogoAdvertenciaAceptarListener ejecuta la acción.
    private void pedirConfirmacionCambio(int tipo, int valor, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            return;
        }
        Bundle argumentos = new Bundle();
        argumentos.putInt(DialogoAdvertencia.KEY_TIPO_BUNDLE, tipo);
        argumentos.putInt(DialogoAdvertencia.KEY_VALOR_BUNDLE, valor);

        DialogoAdvertencia dialogo = new DialogoAdvertencia();
        dialogo.setArguments(argumentos);
        dialogo.setCancelable(false);
        dialogo.show(getSupportFragmentManager(), TAG_DIALOGO_ADVERTENCIA);
    }

    // ==========================================================================
    // BOTONES Y FICHA
    // ==========================================================================

    // Pone en cada botón el texto de lo elegido (o el texto inicial si no hay nada).
    private void repintarBotones() {
        ActualizarBotones.actualizarBotonNombre(btnNombre, personaje.getNombre());
        ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
        ActualizarBotones.actualizarBotonRaza(btnRaza, personaje.getRaza());
        ActualizarBotones.actualizarBotonProfesion(btnProfesion, personaje.getProfesion());
    }

    // Si el personaje está completo: genera poderes (si faltan) y pinta la ficha;
    // si no, la oculta. Se llama después de cualquier cambio en el personaje.
    private void actualizarFichaPersonaje() {
        if (personaje.estaCompleto()) {
            if (!personaje.tienePoderes()) {
                GeneradorPoderes.generarPoderes(personaje);
            }
            pintarDatosEnFicha();
            pintarAvatarEnFicha();
            mostrarFicha();
        } else {
            layoutFicha.setVisibility(View.GONE);
        }
    }

    // Escribe nombre, raza/profesión y poderes en los TextView de la ficha.
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

    // Busca la imagen sexo_raza_profesion en res/drawable; si no existe usa avatar_desconocido.
    private void pintarAvatarEnFicha() {
        String nombreAvatar = GeneradorNombreAvatar.nombreImagen(personaje);
        int idImagen = getResources().getIdentifier(nombreAvatar, "drawable", getPackageName());
        if (idImagen == 0) {
            idImagen = R.drawable.avatar_desconocido;
        }
        ivAvatar.setImageResource(idImagen);
    }

    // Muestra la ficha con un fundido, pero solo si estaba oculta (si ya se ve, no se repite).
    private void mostrarFicha() {
        boolean estaOculta = layoutFicha.getVisibility() != View.VISIBLE;
        if (estaOculta) {
            layoutFicha.setAlpha(0f);
            layoutFicha.setVisibility(View.VISIBLE);
            layoutFicha.animate().alpha(1f).setDuration(400);
        }
    }

    // ==========================================================================
    // ACCIONES SOBRE EL PERSONAJE (tras elegir o confirmar)
    // ==========================================================================

    // Guarda la raza, borra los poderes (se generarán otros) y repinta.
    private void aplicarRaza(int raza) {
        personaje.setRaza(raza);
        personaje.borrarPoderes();
        ActualizarBotones.actualizarBotonRaza(btnRaza, raza);
        actualizarFichaPersonaje();
    }

    // Igual que aplicarRaza, para la profesión.
    private void aplicarProfesion(int profesion) {
        personaje.setProfesion(profesion);
        personaje.borrarPoderes();
        ActualizarBotones.actualizarBotonProfesion(btnProfesion, profesion);
        actualizarFichaPersonaje();
    }

    // Borra los poderes; actualizarFichaPersonaje genera unos nuevos porque ya no hay.
    private void regenerarPoderes() {
        personaje.borrarPoderes();
        actualizarFichaPersonaje();
    }

    // ==========================================================================
    // LISTENERS DE LOS DIÁLOGOS (OnDialogoListener)
    // ==========================================================================

    // Nombre: un texto vacío se guarda como null (= sin nombre).
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

    // Sexo: SIN_ELEGIR (ninguna opción marcada) se ignora.
    @Override
    public void onDialogoSexoGuardarListener(int sexoElegido) {
        if (sexoElegido == Personaje.CHECK_SEXO_SIN_ELEGIR) {
            return;
        }
        personaje.setSexo(sexoElegido);
        ActualizarBotones.actualizarBotonSexo(btnSexo, personaje.getSexo());
        actualizarFichaPersonaje();
    }

    // Raza: si ya hay poderes, el cambio pide confirmación porque se perderán; si no, se aplica directo.
    @Override
    public void onDialogoRazaGuardarListener(int razaElegida) {
        if (razaElegida == Personaje.CHECK_RAZA_SIN_ELEGIR || razaElegida == personaje.getRaza()) {
            return;
        }
        if (personaje.tienePoderes()) {
            pedirConfirmacionCambio(DialogoAdvertencia.CAMBIO_RAZA, razaElegida, TAG_DIALOGO_ADVERTENCIA);
        } else {
            aplicarRaza(razaElegida);
        }
    }

    // Profesión: igual que la raza.
    @Override
    public void onDialogoProfesionGuardarListener(int profesionElegida) {
        if (profesionElegida == Personaje.CHECK_PROFESION_SIN_ELEGIR || profesionElegida == personaje.getProfesion()) {
            return;
        }
        if (personaje.tienePoderes()) {
            pedirConfirmacionCambio(DialogoAdvertencia.CAMBIO_PROFESION, profesionElegida, TAG_DIALOGO_ADVERTENCIA);
        } else {
            aplicarProfesion(profesionElegida);
        }
    }

    // Reiniciar (ya confirmado en su diálogo): borra todo, repinta botones y oculta la ficha.
    @Override
    public void onDialogoReiniciarAceptarListener() {
        personaje.reiniciar();
        repintarBotones();
        actualizarFichaPersonaje();
    }

    // Confirmación aceptada: ejecuta la acción que se había pedido (cambiar raza/profesión o regenerar).
    @Override
    public void onDialogoAdvertenciaAceptarListener(int tipo, int valor) {
        if (tipo == DialogoAdvertencia.CAMBIO_RAZA) {
            aplicarRaza(valor);
        } else if (tipo == DialogoAdvertencia.CAMBIO_PROFESION) {
            aplicarProfesion(valor);
        } else if (tipo == DialogoAdvertencia.GENERAR_PODERES) {
            regenerarPoderes();
        }
    }

    // Cancelar: no hay que hacer nada, el personaje se queda como estaba.
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

    @Override
    public void onDialogoAdvertenciaCancelarListener() {}

}
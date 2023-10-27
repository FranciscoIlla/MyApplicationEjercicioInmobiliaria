package francisco.illa.myapplicationejercicioinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;



import francisco.illa.myapplicationejercicioinmobiliaria.databinding.ActivityMainBinding;
import francisco.illa.myapplicationejercicioinmobiliaria.modelos.Inmueble;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAddInmueble;

    private ArrayList<Inmueble> listaInmuebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaInmuebles = new ArrayList<>();
        inicializarLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherAddInmueble.launch(new Intent(MainActivity.this,AddInmuebleActivity.class));
            }
        });
    }

    private void inicializarLauncher() {
        launcherAddInmueble = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    if(result.getData() != null && result.getData().getExtras() != null){
                        Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                        listaInmuebles.add(inmueble);
                        Toast.makeText(MainActivity.this, inmueble.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "ACCÓN CANCELADA", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package francisco.illa.myapplicationejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import francisco.illa.myapplicationejercicioinmobiliaria.databinding.ActivityAddInmuebleBinding;
import francisco.illa.myapplicationejercicioinmobiliaria.modelos.Inmueble;

public class AddInmuebleActivity extends AppCompatActivity {

    private ActivityAddInmuebleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddInmuebleBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_add_inmueble);

        binding.btnInsertarAddInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Inmueble inmueble = crearInmueble();

                if(inmueble != null){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE",inmueble);
                    intent.putExtras(bundle);

                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(AddInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionAddInmueble.getText().toString().isEmpty() ||
                binding.txtCPAddInmueble.getText().toString().isEmpty() ||
                binding.txtnumeroAddInmueble.getText().toString().isEmpty() ||
                binding.txtCiudadAddInmueble.getText().toString().isEmpty() ||
                binding.txtProvinciaAddInmueble.getText().toString().isEmpty()) {
            return null;
        }

        return new Inmueble(
                binding.txtDireccionAddInmueble.getText().toString(),
                Integer.parseInt(binding.txtnumeroAddInmueble.getText().toString()),
                binding.txtCPAddInmueble.getText().toString(),
                binding.txtCiudadAddInmueble.getText().toString(),
                binding.txtProvinciaAddInmueble.getText().toString(),
                binding.rbPuntuacionAddInmueble.getRating()
        );

    }
}
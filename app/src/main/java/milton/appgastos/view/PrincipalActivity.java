package milton.appgastos.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import milton.appgastos.R;

public class PrincipalActivity extends AppCompatActivity {

    Button btnNovaConta, btnNovaDespesa, btnAddReceita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnNovaConta = (Button) findViewById(R.id.buttonNovaConta);
        btnNovaDespesa = (Button) findViewById(R.id.buttonNovaDespesa);
        btnAddReceita = (Button) findViewById(R.id.buttonNovaReceita);


    }


    public void chamaNovaConta(View view) {

        Intent it = new Intent(this, NovaContaActivity.class);
        startActivity(it);

    }

    public void chamaNovaDespesa(View view) {

        Intent it = new Intent(this, NovaDespesaActivity.class);
        startActivity(it);

    }

    public void chamaAddReceita(View view) {

        Intent it = new Intent(this, AddReceitaActivity.class);
        startActivity(it);


    }


}

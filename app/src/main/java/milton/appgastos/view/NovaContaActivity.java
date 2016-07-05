package milton.appgastos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import milton.appgastos.R;
import milton.appgastos.dao.MyORMLiteHelper;
import milton.appgastos.model.Conta;

public class NovaContaActivity extends AppCompatActivity {

    EditText editTextNomeConta, editTextValorConta;
    Button btnGravar;
    Conta conta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);

        editTextNomeConta = (EditText) findViewById(R.id.editTextNomeConta);
        editTextValorConta = (EditText) findViewById(R.id.editTextValor);

        btnGravar = (Button) findViewById(R.id.btnGravar);

    }


    public void gravarConta(View view) {

        conta = new Conta();

        conta.setNome(editTextNomeConta.getText().toString());
        conta.setValor(Double.parseDouble(editTextValorConta.getText().toString()));

        try {

            Dao<Conta, Integer> contaDao = MyORMLiteHelper.getInstance(NovaContaActivity.this).getContaDao();

            contaDao.create(conta);

            Toast.makeText(NovaContaActivity.this, "Conta cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            Toast.makeText(NovaContaActivity.this, "Erro ao registrar conta: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}

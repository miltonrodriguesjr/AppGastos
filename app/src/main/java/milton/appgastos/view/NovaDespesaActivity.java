package milton.appgastos.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.ArrayList;
import java.util.List;

import milton.appgastos.R;
import milton.appgastos.dao.MyORMLiteHelper;
import milton.appgastos.model.Conta;
import milton.appgastos.model.Despesa;

public class NovaDespesaActivity extends AppCompatActivity {

    ArrayList<Conta> itens;
    ArrayAdapter adapterSpinner;
    Spinner spinnerContas;
    EditText editTextValorDespesa, editTextDescricao;
    Despesa despesa;
    Conta conta;
    Button btnDespesas;
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_despesa);

        spinnerContas = (Spinner) findViewById(R.id.spinnerConta);
        editTextValorDespesa = (EditText) findViewById(R.id.editTextValorDespesa);
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        btnDespesas = (Button) findViewById(R.id.btnDespesas);

        carregaSpinnerContas();


    }

    public void carregaSpinnerContas() {

        try {

            Dao<Conta, Integer> contaDao = MyORMLiteHelper.getInstance(NovaDespesaActivity.this).getContaDao();
            itens = (ArrayList) contaDao.queryForAll();

            //Cria o adapter
            adapterSpinner = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, itens);

            //Define o adapter
            spinnerContas.setAdapter(adapterSpinner);

        } catch (Exception ex) {

            System.out.println("Erro carregaSpinnerContas " + ex.getMessage());
            Toast.makeText(NovaDespesaActivity.this, "Erro carregaSpinnerContas " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public void gravaDespesa(View view) {

        conta = (Conta) spinnerContas.getSelectedItem();
        despesa = new Despesa();

        double novaDespesa = calculaValor(Double.parseDouble(editTextValorDespesa.getText().toString()), conta.getValor());

        conta.setValor(novaDespesa);
        despesa.setValor(Double.parseDouble(editTextValorDespesa.getText().toString()));
        despesa.setDescricao(editTextDescricao.getText().toString());
        despesa.setData(String.valueOf(new java.sql.Date(System.currentTimeMillis())));

        despesa.setConta(conta);

        try {

            Dao<Despesa, Integer> despesaDao = MyORMLiteHelper.getInstance(NovaDespesaActivity.this).getDespesaDao();
            UpdateBuilder<Conta, Integer> updateBuilderConta = MyORMLiteHelper.getInstance(NovaDespesaActivity.this).getContaDao().updateBuilder();


            updateBuilderConta.where().eq("idConta", conta.getId());
            updateBuilderConta.updateColumnValue("valor", conta.getValor());
            updateBuilderConta.update();

            despesaDao.create(despesa);

            Toast.makeText(NovaDespesaActivity.this, "Despesa registrada!!", Toast.LENGTH_SHORT).show();
            carregaSpinnerContas();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            Toast.makeText(NovaDespesaActivity.this, "Erro ao registrar a despesa!!", Toast.LENGTH_SHORT).show();


        }


    }

    public double calculaValor(double valorDespesa, double valorNaConta) {
        double total = valorNaConta - valorDespesa;
        return total;
    }

    public void verificaDespesas(View view) {

        List<Despesa> listDespesa = null;
        ListView listView = (ListView) findViewById(R.id.customListView);

        try {

            conta = (Conta) spinnerContas.getSelectedItem();

            Dao<Despesa, Integer> despesaDao = MyORMLiteHelper.getInstance(NovaDespesaActivity.this).getDespesaDao();
//            listDespesa = (ArrayList) despesaDao.queryForEq("conta_idconta", conta.getId());
            listDespesa = (ArrayList) despesaDao.queryForAll();

            Toast.makeText(NovaDespesaActivity.this, "tamanho da lista " + listDespesa.size(), Toast.LENGTH_SHORT).show();
            ;


            final ArrayAdapter<Despesa> arrayAdapter = new ArrayAdapter<Despesa>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1, listDespesa);

            listView.setAdapter(arrayAdapter);
            //Cria o gerador do AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Detalhes");
            //define a mensagem
            builder.setMessage(".");
            //define um botão como positivo
            builder.setView(listView);

            builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    return;
                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();


        } catch (Exception e) {

        }


    }

}

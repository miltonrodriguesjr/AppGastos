package milton.appgastos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import milton.appgastos.R;
import milton.appgastos.dao.MyORMLiteHelper;
import milton.appgastos.model.Conta;
import milton.appgastos.model.UltimosLancamentos;

public class AddReceitaActivity extends AppCompatActivity {

    Spinner spinnerContas;
    ArrayList<Conta> itens;
    ArrayList<UltimosLancamentos> ultimosLancamentosItens;
    ArrayAdapter adapterSpinner;
    EditText editTextValor;
    Conta conta;
    ListView listViewUltimosLancamentos;
    ArrayAdapter listViewAdapter;
    UltimosLancamentos ultimosLancamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receita);

        spinnerContas = (Spinner) findViewById(R.id.spinnerConta);
        editTextValor = (EditText) findViewById(R.id.editTextValor);

        listViewUltimosLancamentos = (ListView) findViewById(R.id.listViewUltimosLancamentos);

        carregaSpinnerContas();
        carregaUltimosLancamentos();

    }

    public void carregaSpinnerContas() {

        try {

            Dao<Conta, Integer> contaDao = MyORMLiteHelper.getInstance(AddReceitaActivity.this).getContaDao();
            itens = (ArrayList) contaDao.queryForAll();

            //Cria o adapter
            adapterSpinner = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, itens);

            //Define o adapter
            spinnerContas.setAdapter(adapterSpinner);

        } catch (Exception ex) {

            System.out.println("Erro carregaSpinnerContas " + ex.getMessage());
            Toast.makeText(AddReceitaActivity.this, "Erro carregaSpinnerContas " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void carregaUltimosLancamentos() {

        try {

//            QueryBuilder<Conta, Integer> contaBuilder = MyORMLiteHelper.getInstance(AddReceitaActivity.this).getContaDao().queryBuilder();
//            contaBuilder.where().eq("idConta", conta.getId());


            Dao<UltimosLancamentos, Integer> ultimosDao = MyORMLiteHelper.getInstance(AddReceitaActivity.this).getUltimosLancamentosDao();
            ultimosLancamentosItens = (ArrayList) ultimosDao.queryForAll();

            //Cria o adapter
            listViewAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ultimosLancamentosItens);

            //Define o adapter
            listViewUltimosLancamentos.setAdapter(listViewAdapter);

        } catch (Exception e) {

            System.out.println("Erro ao carregar últimos lançamentos: " + e.getMessage());
            Toast.makeText(AddReceitaActivity.this, "Erro ao carregar últimos lançamentos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void addReceita(View view) {

        conta = (Conta) spinnerContas.getSelectedItem();

        //soma o que tem na conta com o valor adicionado
        double novaReceita = somaReceita(Double.parseDouble(editTextValor.getText().toString()), conta.getValor());

        //seta o novo valor para a conta
        conta.setValor(novaReceita);

        //atualiza no banco novo valor para a conta
        try {

            UpdateBuilder<Conta, Integer> updateBuilderConta = MyORMLiteHelper.getInstance(AddReceitaActivity.this).getContaDao().updateBuilder();
            Dao<UltimosLancamentos, Integer> ultimosDao = MyORMLiteHelper.getInstance(AddReceitaActivity.this).getUltimosLancamentosDao();

            updateBuilderConta.where().eq("idConta", conta.getId());
            updateBuilderConta.updateColumnValue("valor", conta.getValor());
            updateBuilderConta.update();

            Calendar cal = new GregorianCalendar();

            cal.setTimeInMillis(System.currentTimeMillis());

            ultimosLancamentos = new UltimosLancamentos();
            ultimosLancamentos.setValor(Double.parseDouble(editTextValor.getText().toString()));
            ultimosLancamentos.setData(cal);
            ultimosLancamentos.setConta(conta);

            ultimosDao.create(ultimosLancamentos);

            Toast.makeText(AddReceitaActivity.this, "Receita adicionada com sucesso!", Toast.LENGTH_SHORT).show();

            //carrega os ultimos lancamentos
            carregaUltimosLancamentos();

        } catch (Exception e) {

            System.out.println("Erro ao adicionar receita " + e.getMessage());
            Toast.makeText(AddReceitaActivity.this, "Erro ao registrar receita: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public double somaReceita(double valorAdicionado, double valorNaConta) {

        double total = valorAdicionado + valorNaConta;

        return total;
    }

    public void voltar(View view) {

        this.finish();

    }


}

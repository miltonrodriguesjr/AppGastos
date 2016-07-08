package milton.appgastos.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Aluno on 05/07/2016.
 */

@DatabaseTable(tableName = "conta")
public class Conta{


    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer idConta;

    @DatabaseField
    private String nome;

    @DatabaseField
    private double valor;

    public Integer getId() {
        return idConta;
    }

    public void setId(Integer id) {
        this.idConta = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome + " | " + valor;
    }
}

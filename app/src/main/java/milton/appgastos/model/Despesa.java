package milton.appgastos.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Aluno on 05/07/2016.
 */
@DatabaseTable(tableName = "despesa")
public class Despesa{


    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField
    private double valor;

    @DatabaseField
    private double descricao;

    @DatabaseField
    private String data;

    @DatabaseField(foreign = true)
    private Conta conta;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getDescricao() {
        return descricao;
    }

    public void setDescricao(double descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", valor=" + valor +
                ", descricao=" + descricao +
                ", data='" + data + '\'' +
                ", conta=" + conta +
                '}';
    }
}

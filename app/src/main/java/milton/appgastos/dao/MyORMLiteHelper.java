package milton.appgastos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import milton.appgastos.model.Conta;
import milton.appgastos.model.Despesa;
import milton.appgastos.model.UltimosLancamentos;

/**
 * Created by Aluno on 10/05/2016.
 */
public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {


    //Nome da base de dados
    private static final String DATABASE_NAME = "conta.db";

    //Versao da base de dados
    private static final int DATABASE_VERSION = 1;

    //Caso você queria ter apenas uma instancia da base de dados.
    private static MyORMLiteHelper mInstance = null;


    private Dao<Conta, Integer> contaDao = null;
    private Dao<Despesa, Integer> despesaDao = null;
    private Dao<UltimosLancamentos, Integer> ultimosLancamentosDao = null;


    public MyORMLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {

            //Criar tabelas no banco de dados
            TableUtils.createTable(connectionSource, Conta.class);
            TableUtils.createTable(connectionSource, Despesa.class);
            TableUtils.createTable(connectionSource, UltimosLancamentos.class);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            TableUtils.dropTable(connectionSource, Conta.class, true);
            TableUtils.dropTable(connectionSource, Despesa.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MyORMLiteHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyORMLiteHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * Para cada tabela criada, Deve ser criado o seu Dao
     *
     * @return
     * @throws SQLException
     */
    public Dao<Conta, Integer> getContaDao() throws SQLException {
        if (contaDao == null) {
            contaDao = getDao(Conta.class);
        }
        return contaDao;
    }

    public Dao<Despesa, Integer> getDespesaDao() throws SQLException {
        if (despesaDao == null) {
            despesaDao = getDao(Despesa.class);
        }
        return despesaDao;
    }

    public Dao<UltimosLancamentos, Integer> getUltimosLancamentosDao() throws SQLException {
        if (ultimosLancamentosDao == null) {
            ultimosLancamentosDao = getDao(UltimosLancamentos.class);
        }
        return ultimosLancamentosDao;
    }

}

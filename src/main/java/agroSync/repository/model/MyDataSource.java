package agroSync.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class MyDataSource {

    /**
     * Obtiene un objeto DataSource configurado para una base de datos MySQL.
     *
     * @return el objeto DataSource configurado
     */
    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS = new MysqlDataSource();
//        mysqlDS.setURL("jdbc:mysql://127.0.0.1:3306/Agro_Sync");
        mysqlDS.setURL("jdbc:mysql://localhost:3306/Agro_Sync");
        mysqlDS.setUser("jose");
        mysqlDS.setPassword("123");

        return mysqlDS;
    }

}
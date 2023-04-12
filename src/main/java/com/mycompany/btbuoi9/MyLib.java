package com.mycompany.btbuoi9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author datcy
 */
public class MyLib {

    public interface Callback {

        void onWork(Connection conn);
    }

    public static class SQLUtil {

        private String strServer;
        private String strDB;
        private ResultSet rs = null;

        public SQLUtil() {
            this.strServer = "dat911zz/dat911zz";
            this.strDB = "NhaSach";
        }

        public SQLUtil(String server, String db) {
            this.strServer = server;
            this.strDB = db;
        }

        public String connString() {
            return "jdbc:sqlserver://" + strServer
                    + ":1433;databaseName=" + strDB
                    + ";user=sa;password=sa";
        }

        private void addParameters(PreparedStatement pstmt, Object[] params) throws SQLException {
            int paramsLength = params.length;
            for (int i = 0; i < paramsLength; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }

        public void beginTransact(Callback callback) {
            int result = 0;
            try {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
                }
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DAT911ZZ:1433;databaseName=NhaSach;user=sa;password=sa");
                conn.setAutoCommit(false);
                callback.onWork(conn);
                conn.commit();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public double execScalar(String query, Object... params) {
            double result[] = new double[1];
            try {
                beginTransact(new Callback() {
                    @Override
                    public void onWork(Connection conn) {
                        try {
                            ResultSet res = null;
                            PreparedStatement preparedStatement = conn.prepareStatement(query);
                            if (params != null) {
                                addParameters(preparedStatement, params);
                            }
                            res = preparedStatement.executeQuery();
                            while (res.next()) {
                                result[0] = res.getDouble(1);
                            }
                        } catch (Exception e) {
                            System.err.println(e);

                        }
                    }
                });
            } catch (Exception e) {
                System.err.println(e);
            }
            return result[0];
        }

        /**
         * How to call? MyLib.SQLUtil sql = new MyLib.SQLUtil();
         * sql.execReader("SELECT * FROM LoaiSach").forEach((Object t) -> {
         * Vector vt = new Vector(); List<Object> tmpList = (List<Object>) t;
         * vt.add(tmpList.get(0).toString()); vt.add(tmpList.get(1).toString());
         * vt.add(tmpList.get(2).toString()); ...
         * vt.add(tmpList.get(n-1).toString()); dtm.addRow(vt); });
         *
         * * * * * *
         * * @param query .SQL Query string
         * @param params... It can be used like array but smarter. Ex: pram1,
         * pram2, pram3,...
         * @return List of Object.
         */
        public List<Object> execReader(String query, Object... params) {
            List<Object> rsl = new ArrayList<Object>();
            try {
                beginTransact(new Callback() {
                    @Override
                    public void onWork(Connection conn) {
                        try {
                            ResultSet res = null;
                            PreparedStatement preparedStatement = conn.prepareStatement(query);
                            if (params != null) {
                                addParameters(preparedStatement, params);
                            }
                            res = preparedStatement.executeQuery();
                            ResultSetMetaData rsm = res.getMetaData();

                            while (res.next()) {
                                List<Object> tmp = new ArrayList<>();
                                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                                    tmp.add(res.getObject(i));
                                }
                                rsl.add(tmp);
                            }
                        } catch (Exception e) {
                            System.err.println(e);

                        }
                    }
                });
            } catch (Exception e) {
                System.err.println(e);
            }
            return rsl;
        }

        public int executeUpdate(String sql, Object... params) {
            int rsl[] = new int[1];
            beginTransact(new Callback() {
                @Override
                public void onWork(Connection conn) {
                    try {

                        ResultSet res = null;
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        if (params != null) {
                            addParameters(preparedStatement, params);
                        }
                        rsl[0] = preparedStatement.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return rsl[0];
        }
    }
}

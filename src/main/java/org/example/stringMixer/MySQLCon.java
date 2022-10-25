package org.example.stringMixer;

import java.sql.*;

public class MySQLCon {
    public static void main(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(StringMixerApplication.get_sql_connection(), StringMixerApplication.get_db_user(), StringMixerApplication.get_db_pwd());

            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM jobs");



        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insert_job(String path){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(StringMixerApplication.get_sql_connection(), StringMixerApplication.get_db_user(), StringMixerApplication.get_db_pwd());

            String sql_insert = "INSERT INTO jobs (path) VALUES (?)";
            PreparedStatement prepared_st = conn.prepareStatement(sql_insert);
            prepared_st.setString(1, path);

            prepared_st.execute();
            conn.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String get_job(int id){
        String path = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(StringMixerApplication.get_sql_connection(), StringMixerApplication.get_db_user(), StringMixerApplication.get_db_pwd());

            String sql_insert = "SELECT path FROM jobs WHERE id=?";
            PreparedStatement prepared_st = conn.prepareStatement(sql_insert);
            prepared_st.setInt(1, id);

            ResultSet result = prepared_st.executeQuery();

            while(result.next()){
                path = result.getString("path");
            }

            conn.close();

        }catch(Exception e){
            System.out.println(e);
        }

        return path;
    }
}

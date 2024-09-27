package org.example.service;

import lombok.SneakyThrows;
import org.example.db_utils.Database;
import org.example.models.Client;

import javax.naming.InvalidNameException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    @SneakyThrows
    public long create(String name){
        ResultSet resultSet ;
        try {
            validateName(name);
        }
        catch (InvalidNameException e){
            e.printStackTrace();
        }
        String sql_request = "INSERT INTO CLIENT (NAME) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_request, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getLong(1);
            }
            else
            {
                throw new SQLException();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String getById(long id){
        ResultSet resultSet;
        String sql_request = "SELECT * FROM CLIENT WHERE ID = ?";
        Connection connection = Database.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql_request)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
                return resultSet.getString(2);
            }
            else{
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name){
        try{
            validateName(name);
        }
        catch (InvalidNameException e){
            throw new RuntimeException(e);
        }
        Connection connection = Database.getInstance().getConnection();
        String sql_request = "UPDATE CLIENT SET NAME = ? WHERE ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql_request)){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id){
        Connection connection = Database.getInstance().getConnection();
        String sql_request = "DELETE FROM CLIENT WHERE ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql_request)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAll(){
        ResultSet resultSet;
     Connection connection = Database.getInstance().getConnection();
     String sql_request = "SELECT * FROM CLIENT";
     List<Client> clients = new ArrayList<>();
     try(PreparedStatement preparedStatement = connection.prepareStatement(sql_request)){
         preparedStatement.executeQuery();
         resultSet = preparedStatement.getResultSet();
         while (resultSet.next()){
             Client client = new Client();
             client.setId(resultSet.getLong(1));
             client.setName(resultSet.getString(2));
             clients.add(client);
         }
     }
     catch (SQLException e){
         throw new RuntimeException(e);
     }
     return clients;
    }

    private void validateName(String name) throws InvalidNameException {
        if(name == null || name.isEmpty()|| name.length()<3|| name.length()>50){
            throw new InvalidNameException();
        }
    }
}

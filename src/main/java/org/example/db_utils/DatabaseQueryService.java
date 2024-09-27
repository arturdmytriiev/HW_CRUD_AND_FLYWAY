package org.example.db_utils;

import org.example.data_response.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private static final String MAX_PROJECT_CLIENT_FOLDER_URL = "src/main/resources/sql/find_max_projects_client.sql";
    private static final String FIND_YOUNGEST_ELDEST_WORKERS_FOLDER_URL = "src/main/resources/sql/find_youngest_eldest_workers.sql";
    private static final String FIND_LONGEST_PROJECT_FOLDER_URL = "src/main/resources/sql/find_longest_project.sql";
    private static final String FIND_MAX_SALARY_WORKER_FOLDER_URL = "src/main/resources/sql/find_max_salary_worker.sql";
    private static final String PROJECT_PRICES_FOLDER_URL = "src/main/resources/sql/print_project_prices.sql";

    public static List<MaxProjectCountClient> findMaxProjectCount() {
        ResultSet result;
        List<MaxProjectCountClient> maxProjectCountList = new ArrayList<>();

        try{
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(MAX_PROJECT_CLIENT_FOLDER_URL));
            String sqlRequest = String.join("\n",sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
                result = statement.executeQuery();
                while(result.next()) {
                    MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                    maxProjectCountClient.setName(result.getString("name"));
                    maxProjectCountClient.setProject_count(result.getInt("project_count"));
                    maxProjectCountList.add(maxProjectCountClient);
                }
           }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            result.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("ResultSet stream could not be closed", ex);
        }

        return maxProjectCountList;
    }

    public static List<CountYoungestEldestWorkers> countOfYoungestEldestWorkers() {
        ResultSet result;
        List<CountYoungestEldestWorkers> countOfYoungestEldestWorkers = new ArrayList<>();
        try{
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(FIND_YOUNGEST_ELDEST_WORKERS_FOLDER_URL));
            String sqlRequest = String.join("\n",sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
                result = statement.executeQuery();
                while(result.next()) {
                    CountYoungestEldestWorkers countYoungestEldestWorkers = new CountYoungestEldestWorkers();
                    countYoungestEldestWorkers.setName(result.getString("name"));
                    countYoungestEldestWorkers.setType(result.getString("type"));
                    countYoungestEldestWorkers.setBirthday(LocalDate.parse(result.getString("birthday")));
                    countOfYoungestEldestWorkers.add(countYoungestEldestWorkers);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            result.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("ResultSet stream could not be closed", ex);
        }
        return countOfYoungestEldestWorkers;
    }

    public static List<LongestProject> countOfLongestProject() {
        ResultSet result;
        List<LongestProject> longestProjectList = new ArrayList<>();
        try{
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(FIND_LONGEST_PROJECT_FOLDER_URL));
            String sqlRequest = String.join("\n",sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest))
            {
                result = statement.executeQuery();
                while (result.next())
                {
                    LongestProject longestProject = new LongestProject();
                    longestProject.setId(result.getInt("id"));
                    longestProject.setMonths(result.getString("months"));
                    longestProjectList.add(longestProject);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            result.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("ResultSet stream could not be closed", ex);
        }

        return longestProjectList;
    }

    public static List<MaxSalaryOfWorker> maxSalaryOfWorkers() {
        ResultSet result;
        List<MaxSalaryOfWorker> maxSalaryOfWorkers = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(FIND_MAX_SALARY_WORKER_FOLDER_URL));
            String sqlRequest = String.join("\n",sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest))
            {
                result = statement.executeQuery();
                while(result.next())
                {
                    MaxSalaryOfWorker maxSalaryOfWorker = new MaxSalaryOfWorker();
                    maxSalaryOfWorker.setSalary(result.getInt("salary"));
                    maxSalaryOfWorker.setName(result.getString("name"));
                    maxSalaryOfWorkers.add(maxSalaryOfWorker);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            result.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("ResultSet stream could not be closed", ex);
        }

        return maxSalaryOfWorkers;
    }

    public static List<ProjectPrices> countOfProjectPrices() {
        ResultSet result;
        List<ProjectPrices> projectPricesList = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(PROJECT_PRICES_FOLDER_URL));
            String sqlRequest = String.join("\n",sqlLines);
            try (PreparedStatement statement = connection.prepareStatement(sqlRequest)){
                result = statement.executeQuery();
                while (result.next())
                {
                    ProjectPrices projectPrices = new ProjectPrices();
                    projectPrices.setProject_id(result.getInt("id"));
                    projectPrices.setPrice(result.getInt("price"));
                    projectPricesList.add(projectPrices);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            result.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("ResultSet stream could not be closed", ex);
        }

        return projectPricesList;
    }



    public static void main(String[] args) {
        for(MaxProjectCountClient maxProjectCountClient : findMaxProjectCount()) {
            System.out.println(maxProjectCountClient.getName() + " " + maxProjectCountClient.getProject_count());
        }
        System.out.println("---------------------------------------------------------------------------");
        for(CountYoungestEldestWorkers countYoungestEldestWorkers : countOfYoungestEldestWorkers()) {
            System.out.println(countYoungestEldestWorkers.getName() + " " + countYoungestEldestWorkers.getType() + " " + countYoungestEldestWorkers.getBirthday());
        }
        System.out.println("-----------------------------------------------------------------------------");
        for(LongestProject longestProject : countOfLongestProject()) {
            System.out.println(longestProject.getId()+ " " + longestProject.getMonths());
        }
        System.out.println("------------------------------------------------------------------------------");
        for (MaxSalaryOfWorker maxSalaryOfWorker : maxSalaryOfWorkers()) {
            System.out.println(maxSalaryOfWorker.getName() + " " + maxSalaryOfWorker.getSalary());
        }
        System.out.println("-------------------------------------------------------------------------------");
        for(ProjectPrices projectPrices : countOfProjectPrices()) {
            System.out.println(projectPrices.getProject_id()+ " "+ projectPrices.getPrice());
        }


    }
}

package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {
    private int id;
    private int course_id;
    private int contents_id;
    private String quiz_question;
    private String quiz_answer;

    private Course course;
    private Contents contents;

    public Quiz(int id, int course_id, int contents_id, String quiz_question ,String quiz_answer) {
        this.id = id;
        this.course_id = course_id;
        this.contents_id = contents_id;
        this.quiz_question = quiz_question;
        this.quiz_answer = quiz_answer;

        this.course = Course.getFetch(course_id);
        this.contents = Contents.getFetch(contents_id);
    }

    public Quiz(int id,  String quiz_question ,String quiz_answer) {
        this.id = id;
        this.quiz_question = quiz_question;
        this.quiz_answer = quiz_answer;

    }
    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setqCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getContents_id() {
        return contents_id;
    }

    public void setqContents_id(int contents_id) {
        this.contents_id = contents_id;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public String getQuiz_question() {
        return quiz_question;
    }

    public void setQuiz_question(String quiz_question) {
        this.quiz_question = quiz_question;
    }

    public String getQuiz_answer() {
        return quiz_answer;
    }

    public void setQuiz_answer(String quiz_answer) {
        this.quiz_answer = quiz_answer;
    }
    public static ArrayList<Quiz> getList(){
        ArrayList<Quiz> quizList = new ArrayList<>();
        String query = "SELECT * FROM quiz";
        Quiz obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int course_id = resultSet.getInt("course_id");
                int contents_id = resultSet.getInt("contents_id");
                String quiz_question = resultSet.getString("question");
                String quiz_answer = resultSet.getString("answer");

                obj = new Quiz(id , course_id, contents_id , quiz_question , quiz_answer);
                quizList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizList;
    }

    public static ArrayList<Quiz> getListByContents(int contentsID){
        ArrayList<Quiz> quizList = new ArrayList<>();
        String query = "SELECT * FROM quiz WHERE contents_id = " + contentsID;
        Quiz obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int course_id = resultSet.getInt("course_id");
                int contents_id = resultSet.getInt("contents_id");
                String quiz_question = resultSet.getString("question");
                String quiz_answer = resultSet.getString("answer");

                obj = new Quiz(id , course_id, contents_id , quiz_question , quiz_answer);
                quizList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizList;
    }


    public static boolean add(int course_id, int contents_id, String question , String answer){
        String query = "INSERT INTO quiz (course_id , contents_id , question , answer) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, course_id);
            preparedStatement.setInt(2, contents_id);
            preparedStatement.setString(3, question);
            preparedStatement.setString(4, answer);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static Quiz getFetch (int id){
        Quiz obj = null;
        String query = "SELECT * FROM quiz WHERE id=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                obj = new Quiz();
                obj.setId(resultSet.getInt("id"));
                obj.setqCourse_id(resultSet.getInt("course_id"));
                obj.setqContents_id(resultSet.getInt("contents_id"));
                obj.setQuiz_question(resultSet.getString("question"));
                obj.setQuiz_answer(resultSet.getString("answer"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM quiz WHERE id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean deleteByContentsID(int contents_id){
        String query = "DELETE FROM quiz WHERE contents_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,contents_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean deleteByCourseId(int course_id){
        String query = "DELETE FROM quiz WHERE course_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,course_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean deleteByCourseID(int course_id){
        String query = "DELETE FROM quiz WHERE course_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,course_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean update(int id , String question , String answer ){
        String query = "UPDATE quiz SET question=?,answer=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1,question);
            preparedStatement.setString(2,answer);
            preparedStatement.setInt(3,id);

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<Quiz> searchQuizList(String query){
        ArrayList<Quiz> quizList = new ArrayList<>();
        Quiz obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                obj = new Quiz(resultSet.getInt("q_id"), resultSet.getInt("course_id"), resultSet.getInt("contents_id"), resultSet.getString("question"), resultSet.getString("answer") );

                quizList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizList;
    }
    public static String searchQuery(int educator_id , String course_id,String contents_id){
        String query = "SELECT * , q.id AS q_id, co.name as course_name," +
                " cs.title as title FROM quiz q , course co , contents cs WHERE co.user_id={{educator_id}} " +
                "AND q.course_id=co.id AND q.contents_id=cs.id AND co.name='{{course_id}}' " +
                "AND cs.title='{{contents_id}}'";
        System.out.println(query);
        query = query.replace("{{educator_id}}", Integer.toString(educator_id));
        query = query.replace("{{course_id}}", course_id);
        query = query.replace("{{contents_id}}", contents_id);
        return query;
    }


}
import java.sql.*;
import java.util.*;

public class mainWin {
    private static final String url = "jdbc:mysql://localhost/ket";
    private static final String user = "maikis";
    private static final String passwd = "igalipoti";

    private List<String> questions = new ArrayList<> ();
    private List<List<String>> answers = new ArrayList<> ();
    private List<List <Boolean>> correct = new ArrayList<> ();
    
    private Connection Connector() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);
    }

    private void getTest() {
        ResultSet questionsDB;
        String questionQuery = "SELECT * FROM questions";
        String answerQuery = "SELECT answer, correct FROM answers WHERE q_id = ";
        try {
            Connection conn = Connector();
            Statement stmt = conn.createStatement();
            Statement ansStmt = conn.createStatement();
            questionsDB = stmt.executeQuery(questionQuery);
            while (questionsDB.next()) {
                int qID = questionsDB.getInt("id");
                //fetching questions from db and putting to lists
                questions.add(questionsDB.getString("question"));
                
                //fetching lists of answers and correctness for qID question 
                List<String> ans = new ArrayList<> ();
                List<Boolean> corr = new ArrayList<> ();
                ResultSet answersDB = ansStmt.executeQuery(answerQuery + qID);
                while (answersDB.next()) {
                    ans.add(answersDB.getString("answer"));
                    corr.add(answersDB.getBoolean("correct"));
                }
                
                //put the answers to map
                answers.add(ans);
                correct.add(corr);
            }            
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    
    private void printQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i+1) + ". " + questions.get(i));
            List<String> ans = answers.get(i);
            List<Boolean> corr = correct.get(i);
            for (int j = 0; j < ans.size(); j++) {
                System.out.printf("%5d) %s (%b)\n", j+1, ans.get(j), corr.get(j));
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        mainWin m = new mainWin();
        m.getTest();
        m.printQuestions();        
    }

}
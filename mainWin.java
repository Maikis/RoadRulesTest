import java.sql.*;
import java.util.*;

public class mainWin {
    private static final String url = Passwords.url;
    private static final String user = Passwords.user;
    private static final String passwd = Passwords.passwd;

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
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\n\n***************************************************");
            System.out.println((i+1) + ". " + questions.get(i));
            List<String> ans = answers.get(i);
            List<Boolean> corr = correct.get(i);
            for (int j = 0; j < ans.size(); j++) {
                System.out.printf("%5d) %s (%b)\n", j+1, ans.get(j), corr.get(j));
            }
            System.out.println();
            
            //read user answer
            System.out.print("Answer (example: 1, 2, 4): ");
            String input = s.nextLine();
            String[] inputArray = input.split(", ");
            
            //initialize the answer boolean array
            Boolean[] userAnsw = new Boolean[corr.size()];
            for (int j = 0; j < userAnsw.length; j++)
                userAnsw[j] = false;
            
            for (int j = 0; j < inputArray.length; j++) {
                int val = Integer.parseInt(inputArray[j]);
                userAnsw[val-1] = true;
            }
            
            //print the result and correct/wrong
            System.out.printf("%14s%18s\n", "Correct answer", "Your answer");
            boolean perfect = false;
            for (int j = 0; j < userAnsw.length; j++) {
                boolean c = corr.get(j);
                boolean u = userAnsw[j];
                System.out.printf("%14s%18s%9s\n", c, u, c == u ? "CORRECT" : "WRONG");
                //if any of answers is wrong, then question is answered wrong
                if (c != u)
                    perfect = false;
            }
            System.out.println("You answered " + (perfect ? "CORRECT" : "WRONG") + "!");
            System.out.println("***************************************************");
        }
    }
    
    public static void main(String[] args) {
        mainWin m = new mainWin();
        m.getTest();
        m.printQuestions();        
    }

}
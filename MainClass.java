import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainClass {

    private JFrame frmRoadRulesTest;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainClass window = new MainClass();
                    window.frmRoadRulesTest.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    
    private DBHandler dbh = new DBHandler();
    
    public MainClass() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    JTextArea txtAns1 = new JTextArea();
    JTextArea txtAns2 = new JTextArea();
    JTextArea txtAns3 = new JTextArea();
    JTextArea txtAns4 = new JTextArea();
    JTextArea txtAns5 = new JTextArea();
    
    JButton ans1Btn = new JButton("1");
    JButton ans2Btn = new JButton("2");
    JButton ans3Btn = new JButton("3");
    JButton ans4Btn = new JButton("4");
    JButton ans5Btn = new JButton("5");
    
    private void initialize() {
        frmRoadRulesTest = new JFrame();
        frmRoadRulesTest.setTitle("Road Rules test BETA v1.0");
        frmRoadRulesTest.getContentPane().setBackground(new Color(102, 153, 204));
        frmRoadRulesTest.setBackground(SystemColor.windowBorder);
        frmRoadRulesTest.setBounds(100, 100, 1100, 670);
        frmRoadRulesTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRoadRulesTest.getContentPane().setLayout(null);
        
        //generate new test
        dbh.generateTest();
        
        JTextArea title = new JTextArea();
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setBackground(new Color(102, 153, 204));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Ubuntu", Font.BOLD, 20));
        title.setText(dbh.getQuestion());
        title.setEditable(false);
        title.setBounds(29, 42, 570, 245);
        frmRoadRulesTest.getContentPane().add(title);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255)));
        panel.setBackground(new Color(102, 153, 204));
        panel.setBounds(29, 299, 1033, 291);
        frmRoadRulesTest.getContentPane().add(panel);
        panel.setLayout(null);
        
        ans1Btn.setEnabled(false);
        ans1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (txtAns1.getForeground() == Color.WHITE) {
                    txtAns1.setForeground(Color.BLACK);
                } else {
                    txtAns1.setForeground(Color.WHITE);
                }
            }
        });
        ans1Btn.setBounds(12, 12, 50, 44);
        panel.add(ans1Btn);
        
        ans2Btn.setEnabled(false);
        ans2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns2.getForeground() == Color.WHITE) {
                    txtAns2.setForeground(Color.BLACK);
                } else {
                    txtAns2.setForeground(Color.WHITE);
                }
            }
        });
        ans2Btn.setBounds(12, 68, 50, 44);
        panel.add(ans2Btn);
        
        ans3Btn.setEnabled(false);
        ans3Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns3.getForeground() == Color.WHITE) {
                    txtAns3.setForeground(Color.BLACK);
                } else {
                    txtAns3.setForeground(Color.WHITE);
                }
            }
        });
        ans3Btn.setBounds(12, 124, 50, 44);
        panel.add(ans3Btn);
        
        ans4Btn.setEnabled(false);
        ans4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns4.getForeground() == Color.WHITE) {
                    txtAns4.setForeground(Color.BLACK);
                } else {
                    txtAns4.setForeground(Color.WHITE);
                }
            }
        });
        ans4Btn.setBounds(12, 180, 50, 44);
        panel.add(ans4Btn);
        
        ans5Btn.setEnabled(false);
        ans5Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns5.getForeground() == Color.WHITE) {
                    txtAns5.setForeground(Color.BLACK);
                } else {
                    txtAns5.setForeground(Color.WHITE);
                }
            }
        });
        ans5Btn.setBounds(12, 236, 50, 44);
        panel.add(ans5Btn);
        
        txtAns1.setEditable(false);
        txtAns1.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns1.setLineWrap(true);
        txtAns1.setBackground(new Color(102, 153, 204));
        txtAns1.setForeground(Color.WHITE);
        txtAns1.setText("-");
        txtAns1.setBounds(75, 12, 946, 44);
        panel.add(txtAns1);
        
        txtAns2.setText("-");
        txtAns2.setLineWrap(true);
        txtAns2.setForeground(Color.WHITE);
        txtAns2.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns2.setEditable(false);
        txtAns2.setBackground(new Color(102, 153, 204));
        txtAns2.setBounds(74, 68, 946, 44);
        panel.add(txtAns2);
        
        txtAns3.setText("-");
        txtAns3.setLineWrap(true);
        txtAns3.setForeground(Color.WHITE);
        txtAns3.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns3.setEditable(false);
        txtAns3.setBackground(new Color(102, 153, 204));
        txtAns3.setBounds(75, 124, 946, 44);
        panel.add(txtAns3);
        
        txtAns4.setText("-");
        txtAns4.setLineWrap(true);
        txtAns4.setForeground(Color.WHITE);
        txtAns4.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns4.setEditable(false);
        txtAns4.setBackground(new Color(102, 153, 204));
        txtAns4.setBounds(75, 180, 946, 44);
        panel.add(txtAns4);
        
        txtAns5.setText("-");
        txtAns5.setLineWrap(true);
        txtAns5.setForeground(Color.WHITE);
        txtAns5.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns5.setEditable(false);
        txtAns5.setBackground(new Color(102, 153, 204));
        txtAns5.setBounds(75, 236, 946, 44);
        panel.add(txtAns5);
        
        JButton previousBtn = new JButton("Previous");
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        previousBtn.setBounds(39, 602, 117, 25);
        frmRoadRulesTest.getContentPane().add(previousBtn);
        
        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(168, 602, 117, 25);
        frmRoadRulesTest.getContentPane().add(nextBtn);
        
        JButton finishTestBtn = new JButton("Finish test");
        finishTestBtn.setBounds(945, 602, 117, 25);
        frmRoadRulesTest.getContentPane().add(finishTestBtn);
    
        fillAnswers();
    }
    
    /*
     * method for filling answer fields with values
     */
    private void fillAnswers() {
        List<String> ans = dbh.getAnswers();
        
        for (int i = 0; i < ans.size(); i++) {
            String s = ans.get(i);
            switch (i) {
            case 0:
                txtAns1.setText(s);
                ans1Btn.setEnabled(true);
                break;
            case 1:
                txtAns2.setText(s);
                ans2Btn.setEnabled(true);
                break;
            case 2:
                txtAns3.setText(s);
                ans3Btn.setEnabled(true);
                break;
            case 3:
                txtAns4.setText(s);
                ans4Btn.setEnabled(true);
                break;
            case 4:
                txtAns5.setText(s);
                ans5Btn.setEnabled(true);
                break;
            }
        }
    }
}

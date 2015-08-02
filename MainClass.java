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
import javax.swing.JLabel;

<<<<<<< HEAD
public class MainClass implements ActionListener {

	private JFrame win;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass window = new MainClass();
					window.win.setVisible(true);
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
	JTextArea question = new JTextArea();

	JTextArea[] txtAns = new JTextArea[5];

	JButton[] ansBtn = new JButton[5];
	/*
	 * JButton ans1Btn = new JButton("1"); JButton ans2Btn = new JButton("2");
	 * JButton ans3Btn = new JButton("3"); JButton ans4Btn = new JButton("4");
	 * JButton ans5Btn = new JButton("5");
	 */

	/*
	 * COLORS
	 */

	// main background color
	Color mainBg = new Color(240, 240, 240);
	// text color
	Color txtFg = new Color(0, 0, 0);
	// marked text foreground color
	Color markedTxtFg = new Color(220, 220, 220);

	Color btnBgColor = new Color(102, 153, 204);
	Color btnColor = new Color(238, 238, 238);

	private JLabel verdict = new JLabel("-");

	private void initialize() {
		win = new JFrame();
		win.setTitle("Road Rules test BETA v1.0");
		win.getContentPane().setBackground(mainBg);
		win.setBackground(SystemColor.windowBorder);
		win.setBounds(100, 100, 1100, 670);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setResizable(false);
		win.getContentPane().setLayout(null);

		// generate new test
		dbh.generateTest();

		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setBackground(mainBg);
		question.setForeground(txtFg);
		question.setFont(new Font("Ubuntu", Font.BOLD, 20));
		question.setText("--");
		question.setEditable(false);
		question.setBounds(29, 42, 570, 245);
		win.getContentPane().add(question);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(mainBg);
		panel.setBounds(29, 299, 1033, 291);
		win.getContentPane().add(panel);
		panel.setLayout(null);

		//dimensions of answer buttons
		int btnX = 12, btnY = 12;
		int btnWidth = 50, btnHeight = 44;
		
		//dimensions of answer texts
		int ansTxtX = 75, ansTxtY = 12;
		int ansTxtWidth = 946, ansTxtHeight = btnHeight;
		
		for (int i = 0; i < txtAns.length; i++) {
			// answer texts
			txtAns[i] = new JTextArea();
			txtAns[i].setEditable(false);
			txtAns[i].setFont(new Font("Ubuntu", Font.BOLD, 16));
			txtAns[i].setLineWrap(true);
			txtAns[i].setBackground(mainBg);
			txtAns[i].setForeground(txtFg);
			txtAns[i].setText("-");
			txtAns[i].setBounds(ansTxtX, ansTxtY, ansTxtWidth, ansTxtHeight);
			panel.add(txtAns[i]);

			// answer buttons
			ansBtn[i] = new JButton();
			ansBtn[i].setText(""+(i+1));
			ansBtn[i].setEnabled(false);
			ansBtn[i].setBounds(btnX, btnY, btnWidth, btnHeight);
			panel.add(ansBtn[i]);
			
			btnY += 56;
			ansTxtY += 56;
		}

		JButton previousBtn = new JButton("Previous");
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbh.getQIndex() != 0) {
					dbh.decreaseIndex();
					fillAnswers();
				}
			}
		});
		previousBtn.setBounds(39, 602, 117, 25);
		win.getContentPane().add(previousBtn);

		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dbh.getQIndex() != 1) {
					markAnsweredButtons(5, true);
					dbh.increaseIndex();
					fillAnswers();
				}
			}
		});
		nextBtn.setBounds(168, 602, 117, 25);
		win.getContentPane().add(nextBtn);

		JButton finishTestBtn = new JButton("Finish test");
		finishTestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				win.dispose();
				System.exit(0);
			}
		});
		finishTestBtn.setBounds(945, 602, 117, 25);
		win.getContentPane().add(finishTestBtn);

		JButton btnCheckAnswer = new JButton("Check Answer");
		btnCheckAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCheckedAns();
			}
		});
		btnCheckAnswer.setBounds(353, 602, 169, 25);
		win.getContentPane().add(btnCheckAnswer);

		verdict.setForeground(txtFg);
		verdict.setBounds(611, 607, 70, 15);
		win.getContentPane().add(verdict);

		fillAnswers();
	}

	/*
	 * overriden ActionListener interface method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < txtAns.length; i++) {
			if (txtAns[i].getBackground() == mainBg) {
				txtAns[i].setBackground(markedTxtFg);
				dbh.setAnswer(true, 0);
			} else {
				txtAns[i].setBackground(mainBg);
				dbh.setAnswer(false, 0);
			}
		}
	};

	/*
	 * method for filling answer fields with values
	 */
	private void fillAnswers() {
		question.setText(dbh.getQuestion());
		List<String> ans = dbh.getAnswers();
		List<Boolean> corr = dbh.getCorrect();
		Boolean[] userAns = new Boolean[corr.size()];
		for (int i = 0; i < userAns.length; i++)
			userAns[i] = false;

		dbh.setUserAnswers(userAns);

		for (int i = 0; i < ans.size(); i++) {
			String s = ans.get(i);
			txtAns[i].setText(s);
			ansBtn[i].setEnabled(true);
		}
	}

	/*
	 * temporary method to check answers and display result
	 */
	private void displayCheckedAns() {
		List<Boolean> corr = dbh.getCorrect();
		Boolean[] userInput = dbh.getUserAnswers();
		boolean totallyCorrect = true;

		for (int i = 0; i < corr.size(); i++) {
			// if answered wrong...
			if (userInput[i] != corr.get(i))
				totallyCorrect = false;

			// display correct and not correct answers for user
			if (corr.get(i)) {
				markAnsweredButtons(i, true);
			} else {
				markAnsweredButtons(i, false);
			}
		}
		verdict.setText(totallyCorrect ? "CORRECT!" : "WRONG!");
	}

	/*
	 * temporary helper method for button coloring
	 */
	private void markAnsweredButtons(int i, boolean b) {
		if (i < 5) {
			ansBtn[i].setEnabled(false);
			if (b)
				ansBtn[i].setBackground(Color.GREEN);
			else if (!b)
				ansBtn[i].setBackground(Color.RED);
		} else {
			txtAns[i].setBackground(mainBg);
			ansBtn[i].setBackground(btnColor);
			ansBtn[i].setEnabled(false);
		}
	}
}
=======
public class MainClass {

    private JFrame win;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainClass window = new MainClass();
                    window.win.setVisible(true);
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
    JTextArea question = new JTextArea();
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
    
    Color backgroundColor = new Color(102, 153, 204);
    Color btnColor = new Color(238, 238, 238);
    private JLabel verdict = new JLabel("-");
    
    private void initialize() {
        win = new JFrame();
        win.setTitle("Road Rules test BETA v1.0");
        win.getContentPane().setBackground(new Color(102, 153, 204));
        win.setBackground(SystemColor.windowBorder);
        win.setBounds(100, 100, 1100, 670);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.getContentPane().setLayout(null);
        
        //generate new test
        dbh.generateTest();
        
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setBackground(new Color(102, 153, 204));
        question.setForeground(Color.WHITE);
        question.setFont(new Font("Ubuntu", Font.BOLD, 20));
        question.setText("--");
        question.setEditable(false);
        question.setBounds(29, 42, 570, 245);
        win.getContentPane().add(question);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255)));
        panel.setBackground(new Color(102, 153, 204));
        panel.setBounds(29, 299, 1033, 291);
        win.getContentPane().add(panel);
        panel.setLayout(null);
        ans1Btn.setEnabled(false);
        ans1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (txtAns1.getBackground() == backgroundColor) {
                    txtAns1.setBackground(Color.BLACK);
                    dbh.setAnswer(true, 0);
                } else {
                    txtAns1.setBackground(backgroundColor);
                    dbh.setAnswer(false, 0);
                }
            }
        });
        ans1Btn.setBounds(12, 12, 50, 44);
        panel.add(ans1Btn);
        
        ans2Btn.setEnabled(false);
        ans2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns2.getBackground() == backgroundColor) {
                    txtAns2.setBackground(Color.BLACK);
                    dbh.setAnswer(true, 1);
                } else {
                    txtAns2.setBackground(backgroundColor);
                    dbh.setAnswer(false, 1);
                }
            }
        });
        ans2Btn.setBounds(12, 68, 50, 44);
        panel.add(ans2Btn);
        
        ans3Btn.setEnabled(false);
        ans3Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns3.getBackground() == backgroundColor) {
                    txtAns3.setBackground(Color.BLACK);
                    dbh.setAnswer(true, 2);
                } else {
                    txtAns3.setBackground(backgroundColor);
                    dbh.setAnswer(false, 2);
                }
            }
        });
        ans3Btn.setBounds(12, 124, 50, 44);
        panel.add(ans3Btn);
        
        ans4Btn.setEnabled(false);
        ans4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns4.getBackground() == backgroundColor) {
                    txtAns4.setBackground(Color.BLACK);
                    dbh.setAnswer(true, 3);
                } else {
                    txtAns4.setBackground(backgroundColor);
                    dbh.setAnswer(false, 3);
                }
            }
        });
        ans4Btn.setBounds(12, 180, 50, 44);
        panel.add(ans4Btn);
        
        ans5Btn.setEnabled(false);
        ans5Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtAns5.getBackground() == backgroundColor) {
                    txtAns5.setBackground(Color.BLACK);
                    dbh.setAnswer(true, 4);
                } else {
                    txtAns5.setBackground(backgroundColor);
                    dbh.setAnswer(false, 4);
                }
            }
        });
        ans5Btn.setBounds(12, 236, 50, 44);
        panel.add(ans5Btn);
        
        txtAns1.setEditable(false);
        txtAns1.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns1.setLineWrap(true);
        txtAns1.setBackground(backgroundColor);
        txtAns1.setForeground(Color.WHITE);
        txtAns1.setText("-");
        txtAns1.setBounds(75, 12, 946, 44);
        panel.add(txtAns1);
        
        txtAns2.setText("-");
        txtAns2.setLineWrap(true);
        txtAns2.setForeground(Color.WHITE);
        txtAns2.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns2.setEditable(false);
        txtAns2.setBackground(backgroundColor);
        txtAns2.setBounds(74, 68, 946, 44);
        panel.add(txtAns2);
        
        txtAns3.setText("-");
        txtAns3.setLineWrap(true);
        txtAns3.setForeground(Color.WHITE);
        txtAns3.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns3.setEditable(false);
        txtAns3.setBackground(backgroundColor);
        txtAns3.setBounds(75, 124, 946, 44);
        panel.add(txtAns3);
        
        txtAns4.setText("-");
        txtAns4.setLineWrap(true);
        txtAns4.setForeground(Color.WHITE);
        txtAns4.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns4.setEditable(false);
        txtAns4.setBackground(backgroundColor);
        txtAns4.setBounds(75, 180, 946, 44);
        panel.add(txtAns4);
        
        txtAns5.setText("-");
        txtAns5.setLineWrap(true);
        txtAns5.setForeground(Color.WHITE);
        txtAns5.setFont(new Font("Ubuntu", Font.BOLD, 16));
        txtAns5.setEditable(false);
        txtAns5.setBackground(backgroundColor);
        txtAns5.setBounds(75, 236, 946, 44);
        panel.add(txtAns5);
        
        JButton previousBtn = new JButton("Previous");
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        previousBtn.setBounds(39, 602, 117, 25);
        win.getContentPane().add(previousBtn);
        
        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (dbh.getQIndex() != 1) {
                    markAnsweredButtons(5, true);
                    dbh.increaseIndex();
                    fillAnswers();
                }
            }
        });
        nextBtn.setBounds(168, 602, 117, 25);
        win.getContentPane().add(nextBtn);
        
        JButton finishTestBtn = new JButton("Finish test");
        finishTestBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                win.dispose();
                System.exit(0);
            }
        });
        finishTestBtn.setBounds(945, 602, 117, 25);
        win.getContentPane().add(finishTestBtn);
        
        JButton btnCheckAnswer = new JButton("Check Answer");
        btnCheckAnswer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayCheckedAns();
            }
        });
        btnCheckAnswer.setBounds(353, 602, 169, 25);
        win.getContentPane().add(btnCheckAnswer);
        
        verdict.setForeground(Color.WHITE);
        verdict.setBounds(611, 607, 70, 15);
        win.getContentPane().add(verdict);
    
        fillAnswers();
    }
    
    /*
     * method for filling answer fields with values
     */
    private void fillAnswers() {
        question.setText(dbh.getQuestion());
        List<String> ans = dbh.getAnswers();
        List<Boolean> corr = dbh.getCorrect();
        Boolean[] userAns = new Boolean[corr.size()];
        for (int i = 0; i < userAns.length; i++)
            userAns[i] = false;
            
        dbh.setUserAnswers(userAns);
        
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
    
    /*
     * temporary method to check answers and display result
     */
    private void displayCheckedAns() {
        List<Boolean> corr = dbh.getCorrect();
        Boolean[] userInput = dbh.getUserAnswers();
        boolean totallyCorrect = true;
        
        for (int i = 0; i < corr.size(); i++) {
            //if answered wrong...
            if (userInput[i] != corr.get(i))
                totallyCorrect = false;
            
            //display correct and not correct answers for user
            if (corr.get(i)) {
                markAnsweredButtons(i, true);
            } else {
                markAnsweredButtons(i, false);
            }
        }
        verdict.setText(totallyCorrect ? "CORRECT!" : "WRONG!");
    }
    
    /*
     * temporary helper method for button coloring
     */
    private void markAnsweredButtons(int i, boolean b) {
        switch (i) {
        case 0:
            if (b) ans1Btn.setBackground(Color.GREEN);
            else if (!b) ans1Btn.setBackground(Color.RED);
            break;
        case 1:
            if (b) ans2Btn.setBackground(Color.GREEN);
            else if (!b) ans2Btn.setBackground(Color.RED);
            break;
        case 2:
            if (b) ans3Btn.setBackground(Color.GREEN);
            else if (!b) ans3Btn.setBackground(Color.RED);
            break;
        case 3:
            if (b) ans4Btn.setBackground(Color.GREEN);
            else if (!b) ans4Btn.setBackground(Color.RED);
            break;
        case 4:
            if (b) ans5Btn.setBackground(Color.GREEN);
            else if (!b) ans5Btn.setBackground(Color.RED);
            break;
        case 5:
            txtAns1.setBackground(backgroundColor);
            ans1Btn.setBackground(btnColor);
            ans1Btn.setEnabled(false);
            txtAns2.setBackground(backgroundColor);
            ans2Btn.setBackground(btnColor);
            ans2Btn.setEnabled(false);
            txtAns3.setBackground(backgroundColor);
            ans3Btn.setBackground(btnColor);
            ans3Btn.setEnabled(false);
            txtAns4.setBackground(backgroundColor);
            ans4Btn.setBackground(btnColor);
            ans4Btn.setEnabled(false);
            txtAns5.setBackground(backgroundColor);
            ans5Btn.setBackground(btnColor);
            ans5Btn.setEnabled(false);
            break;
        }
    }
}
>>>>>>> 105f4a14b0ffbc44fae02732fa519f46ba609b59

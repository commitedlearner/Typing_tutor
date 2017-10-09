package july10;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import lecture4.datatypes;

public class typingtutor extends JFrame implements ActionListener {
	boolean running;
	int timeremaining;
	int score;
	Timer timer;
	String[] data;
	JLabel lbltimer;
	JLabel lblscore;
	JLabel lblword;
	JTextField txttext;
	JButton btnstart;
	JButton btnstop;

	public typingtutor(String[] data) {
		this.data = data;
		super.setSize(1000, 1000);
		super.setTitle("typing tutor");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setExtendedState(MAXIMIZED_BOTH);
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);
		Font font = new Font("Comic Sans MS", 1, 150);
		lbltimer = new JLabel("timer");
		lbltimer.setFont(font);
		super.add(lbltimer);

		lblscore = new JLabel("score");
		lblscore.setFont(font);
		super.add(lblscore);

		lblword = new JLabel("word");
		lblword.setFont(font);
		super.add(lblword);

		txttext = new JTextField("text");
		txttext.setFont(font);
		super.add(txttext);

		btnstart = new JButton("start");
		btnstart.setFont(font);
		btnstart.addActionListener(this);
		super.add(btnstart);

		btnstop = new JButton();
		btnstop.setFont(font);
		btnstop.addActionListener(this);
		super.add(btnstop);
		gameup();
		super.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnstart) {
			handlestart();
		} else if (e.getSource() == btnstop) {
			handlestop();
		}
		if (e.getSource() == timer) {
			handletimer();
		}
	}

	private void handletimer() {
		if (timeremaining > 0) {
			timeremaining--;

			String expected = txttext.getText();
			String actual = lblword.getText();
			if (actual.equals(expected) && actual.length() > 0) {
				score++;
			}
			lbltimer.setText("timer" + timeremaining);
			lblscore.setText("score" + score);
			lblword.setText(data[(int) (Math.random() * data.length)]);
			txttext.setText("");
			txttext.setFocusable(true);
		} else {
			handlestop();
		}
	}

	private void handlestop() {
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "replay");
		if (choice == JOptionPane.YES_OPTION) {
			gameup();
		} else if(choice == JOptionPane.NO_OPTION) {
			super.dispose();
		}
		else{
			
		}
	}

	private void handlestart() {
		if (running == false) {
			running = true;
			timer.start();
			txttext.setEnabled(true);
			btnstart.setText("pause");
			btnstop.setEnabled(true);
		} else {
			running = false;
			txttext.setEnabled(false);
			timer.stop();
			btnstart.setText("start");
		}
	}
  
	public void gameup() {
		running = false;
		timeremaining = 50;
		score = 0;
		timer = new Timer(2000, this);

		lbltimer.setText("timer" + timeremaining);
		lblscore.setText("score" + score);
		lblword.setText("");
		txttext.setText("");
		txttext.setEnabled(false);
		btnstart.setText("start");
		btnstop.setText("stop");
		btnstop.setEnabled(false);
	}

}
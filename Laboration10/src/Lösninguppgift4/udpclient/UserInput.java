package Lösninguppgift4.udpclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserInput extends JPanel implements ActionListener {
	private JButton btnCalculate = new JButton("Beräkna");
	private JLabel lblTitle = new JLabel("Inmatning av matematiska uttryck");
	private JLabel lblResult = new JLabel(" "); 
	private JLabel lblOp1 = new JLabel("Operand 1:");
	private JLabel lblOp2 = new JLabel("Operand 2:");
	private JTextField tfNbr1 = new JTextField();
	private JTextField tfNbr2 = new JTextField();
	private JRadioButton rbAdd = new JRadioButton("+");
	private JRadioButton rbSub = new JRadioButton("-");
	private JRadioButton rbMul = new JRadioButton("*");
	private JRadioButton rbDiv = new JRadioButton("/");
	private ButtonGroup group = new ButtonGroup();
	private ClientController controller;

	public UserInput(ClientController controller) {
		this.controller = controller;
		setLayout(new BorderLayout());

		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnCalculate.addActionListener(this);

		add(lblTitle,BorderLayout.NORTH);
		add(operationPanel(), BorderLayout.EAST);
		add(centerPanel(), BorderLayout.CENTER);
		add(lblResult, BorderLayout.SOUTH);
	}

	private JPanel centerPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(operandPanel(),BorderLayout.CENTER);
		panel.add(btnCalculate,BorderLayout.SOUTH);
		return panel;
	}

	private JPanel operandPanel() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		panel.setBorder(BorderFactory.createTitledBorder("Operander"));
		tfNbr1.setHorizontalAlignment(JTextField.RIGHT);
		tfNbr1.setText("0");
		tfNbr2.setHorizontalAlignment(JTextField.RIGHT);
		tfNbr2.setText("0");
		panel.add(lblOp1);
		panel.add(tfNbr1);
		panel.add(lblOp2);
		panel.add(tfNbr2);
		return panel;
	}

	private JPanel operationPanel() {
		JPanel panel = new JPanel(new GridLayout(4,1));
		panel.setPreferredSize(new Dimension(60,50));
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		rbAdd.setSelected(true);
		group.add(rbAdd);
		group.add(rbSub);
		group.add(rbMul);
		group.add(rbDiv);
		panel.add(rbAdd);
		panel.add(rbSub);
		panel.add(rbMul);
		panel.add(rbDiv);
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		String operation;
		if (rbAdd.isSelected()) {
			operation = "+";
		} else if (rbSub.isSelected()) {
			operation = "-";
		} else if (rbMul.isSelected()) {
			operation = "*";
		} else {
			operation = "/";
		}
		controller.newCalculation(tfNbr1.getText(), tfNbr2.getText(), operation);
	}

	public void setResult(String result) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				lblResult.setText(result);
			}
		});
	}

}

package money;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BankManager {
	private static JTextField idt;
	private static JTextField namet;
	private static JTextField aget;
	private static JTextField telt;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300,500);
		FlowLayout flow = new FlowLayout();
		f.getContentPane().setLayout(flow);
		
		JLabel lblNewLabel = new JLabel("   ID  ");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblNewLabel);
		
		idt = new JTextField();
		idt.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(idt);
		idt.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblName);
		
		namet = new JTextField();
		namet.setFont(new Font("굴림", Font.PLAIN, 24));
		namet.setColumns(10);
		f.getContentPane().add(namet);
		
		JLabel lblAge = new JLabel(" AGE ");
		lblAge.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblAge);
		
		aget = new JTextField();
		aget.setFont(new Font("굴림", Font.PLAIN, 24));
		aget.setColumns(10);
		f.getContentPane().add(aget);
		
		JLabel lblTel = new JLabel(" TEL ");
		lblTel.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblTel);
		
		telt = new JTextField();
		telt.setFont(new Font("굴림", Font.PLAIN, 24));
		telt.setColumns(10);
		f.getContentPane().add(telt);
		
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BankDTO dto = new BankDTO();
				BankDAO dao = new BankDAO();
				String id = idt.getText();
				String name = namet.getText();
				String age = aget.getText();
				String tel = telt.getText();
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
				
				dao.insert(dto);
			}
		});
		
				JTextArea tt = new JTextArea();
				f.getContentPane().add(tt);
		create.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(create);
		
		JButton select = new JButton("Read");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDAO dao = new BankDAO();
				String id = idt.getText();
				
				BankDTO dto = dao.select(id);
				
				tt.setText("ID =" + dto.getId() + " NAME ="+dto.getName()+ " AGE =" + dto.getAge() + " TEL ="+ dto.getTel());
			}
		});
		select.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(select);
		
		JButton selectAll = new JButton("ReadAll");
		selectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDAO dao = new BankDAO();
				ArrayList list = dao.selectAll();
				
				 for (int j = 0; j < list.size(); j++) { 
					 BankDTO dto = (BankDTO)list.get(j);
					 String a = tt.getText();
					 a+="ID =" + dto.getId() + " NAME ="+dto.getName()+ " AGE =" +dto.getAge() + " TEL ="+ dto.getTel()+ "\n";
					 tt.setText(a);
				 }
					
			}
				
		});
		selectAll.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(selectAll);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDAO dao = new BankDAO();
				String id = idt.getText();
				String value = telt.getText(); 
				dao.update(id, "tel", value);
			}
		});
		update.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BankDAO dao = new BankDAO();
				String id = idt.getText();
				dao.delete(id);
			}
		});
		delete.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(delete);
		
		
		
		
		
		f.setVisible(true);

	}

}

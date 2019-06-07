package money;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class BankManager {
	private static JTextField idt;
	private static JTextField namet;
	private static JTextField aget;
	private static JTextField telt;
	private static JTextField idu;
	private static JTextField columns;
	private static JTextField valuet;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300,580);
		f.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(70, 30);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblNewLabel);
		
		idt = new JTextField();
		idt.setSize(150, 30);
		idt.setLocation(100, 10);
		idt.setHorizontalAlignment(SwingConstants.CENTER);
		idt.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(idt);
		idt.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setSize(70, 30);
		lblName.setLocation(12, 45);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblName);
		
		namet = new JTextField();
		namet.setLocation(100, 45);
		namet.setSize(150, 30);
		namet.setHorizontalAlignment(SwingConstants.CENTER);
		namet.setFont(new Font("굴림", Font.PLAIN, 24));
		namet.setColumns(10);
		f.getContentPane().add(namet);
		
		JLabel lblAge = new JLabel(" AGE ");
		lblAge.setLocation(12, 80);
		lblAge.setSize(70, 30);
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblAge);
		
		aget = new JTextField();
		aget.setLocation(100, 80);
		aget.setSize(150, 30);
		aget.setHorizontalAlignment(SwingConstants.CENTER);
		aget.setFont(new Font("굴림", Font.PLAIN, 24));
		aget.setColumns(10);
		f.getContentPane().add(aget);
		
		JLabel lblTel = new JLabel(" TEL ");
		lblTel.setLocation(12, 115);
		lblTel.setSize(70, 30);
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTel.setFont(new Font("굴림", Font.PLAIN, 24));
		f.getContentPane().add(lblTel);
		
		telt = new JTextField();
		telt.setLocation(100, 115);
		telt.setSize(150, 30);
		telt.setHorizontalAlignment(SwingConstants.CENTER);
		telt.setFont(new Font("굴림", Font.PLAIN, 24));
		telt.setColumns(10);
		f.getContentPane().add(telt);
		
		
		JButton create = new JButton("Create");
		create.setLocation(32, 155);
		create.setSize(100, 30);
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
				tt.setLocation(12, 376);
				tt.setSize(260, 150);
				tt.setBorder(new BevelBorder(BevelBorder.RAISED));
				f.getContentPane().add(tt);
		create.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(create);
		
		JButton select = new JButton("Read");
		select.setLocation(32, 193);
		select.setSize(100, 30);
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
		selectAll.setLocation(157, 193);
		selectAll.setSize(100, 30);
		selectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tt.setText("");
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
		update.setLocation(89, 336);
		update.setSize(100, 30);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDAO dao = new BankDAO();
				String id = idu.getText();
				String changeid = columns.getText();
				String value = valuet.getText(); 
				dao.update(id, changeid, value);
				BankDTO dto = dao.select(id);
				tt.setText(changeid + " = " + value);
			}
		});
		update.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(update);
		
		JButton delete = new JButton("Delete");
		delete.setLocation(157, 155);
		delete.setSize(100, 30);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BankDAO dao = new BankDAO();
				String id = idt.getText();
				dao.delete(id);
			}
		});
		delete.setFont(new Font("굴림", Font.PLAIN, 21));
		f.getContentPane().add(delete);
		
		JLabel lblUpdateId = new JLabel("   Update ID");
		lblUpdateId.setLocation(22, 226);
		lblUpdateId.setSize(120, 30);
		lblUpdateId.setFont(new Font("굴림", Font.PLAIN, 20));
		f.getContentPane().add(lblUpdateId);
		
		idu = new JTextField();
		idu.setLocation(142, 226);
		idu.setSize(130, 30);
		idu.setFont(new Font("굴림", Font.PLAIN, 20));
		idu.setColumns(10);
		f.getContentPane().add(idu);
		
		JLabel lblColumns = new JLabel("   Columns  ");
		lblColumns.setLocation(22, 261);
		lblColumns.setSize(120, 30);
		lblColumns.setFont(new Font("굴림", Font.PLAIN, 20));
		f.getContentPane().add(lblColumns);
		
		columns = new JTextField();
		columns.setLocation(142, 261);
		columns.setSize(130, 30);
		columns.setFont(new Font("굴림", Font.PLAIN, 20));
		columns.setColumns(10);
		f.getContentPane().add(columns);
		
		JLabel lblValue = new JLabel("    Value    ");
		lblValue.setLocation(22, 296);
		lblValue.setSize(120, 30);
		lblValue.setFont(new Font("굴림", Font.PLAIN, 20));
		f.getContentPane().add(lblValue);
		
		valuet = new JTextField();
		valuet.setLocation(142, 296);
		valuet.setSize(130, 30);
		valuet.setFont(new Font("굴림", Font.PLAIN, 20));
		valuet.setColumns(10);
		f.getContentPane().add(valuet);
		
		
		
		
		
		f.setVisible(true);

	}

}

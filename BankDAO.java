package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BankDAO {
	Connection con;
	PreparedStatement ps;
	String url;
	String user;
	String password;
	String DB="bank";
	String table="member";
	ResultSet rs;
	

	public void start() {
		
		System.out.println("1 DB 연결 OK");
		//1.드라이버 설정
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("2. 드라이버 설정 Ok");
			
			
			//2.DB 설정(DB명, id,pw)
			url = "jdbc:mysql://localhost:3306/"+DB;
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList selectAll() {
		ArrayList list = new ArrayList();
		
		start();
		
		BankDTO dto = null; //변수의 생존범위 = 선언의 위치
		try {
			
			//3. SQL문 결정(객체화)
			String sql = "select * from "+table;
			ps = con.prepareStatement(sql);
			System.out.println("SQL문 객체화 OK");
			//4. SQL문 전송
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new BankDTO();
				String id =rs.getString(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String tel = rs.getString(4);
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
				
				list.add(dto);
			}
			System.out.println("SQL문 전송 OK");
			
		} catch (Exception e) {
			System.out.println("DB 처리중 에러발생....");
			System.out.println(e.getMessage());
		}
		finally {
				//에러 발생 여부와 상관없이 무조건 실행시켜야 하는 코드.
			//5. SQL문의 결과가 있으면 받아서 처리
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("자운 해제 중 에러 발생!!");
			}
		
		
	}
		return list;
}	
	
	public void insert(BankDTO dto){
		
		start();
		
		//1.드라이버 설정 , 연걸자
		try {
			
			String sql = "insert into "+table+" values(?,?,?,?)";
			ps = con.prepareStatement(sql);
			System.out.println("3. SQL문 결정 ok");
			
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getAge());
			ps.setString(4, dto.getTel());
			
			ps.executeUpdate();
			System.out.println("4번. SQL문 전송 OK.....");		
			//3.SQL 선택
			//4.SQL 전송
		} catch (Exception e) {
			System.out.println("DB 처리중 에러발생....");
			System.out.println(e.getMessage());
		}
		
	}
	public void update(String id,String changeid, String value) {
		
		start();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/"+DB;
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			
			String sql = "update "+table+" set "+changeid+"= '"+value+"' where  id = '"+id+"'";
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.executeUpdate();
			
		}catch(Exception e){
			
		}
	}
	
	public void delete(String id){
		start();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/"+DB;
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			

			String sql = "delete from "+table+" where id = '"+id+"'";
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
			
		}
	}
	
	

	public BankDTO select(String inputnum) {
		BankDTO dto = null; //변수의 생존범위 = 선언의 위치
		start();

		try {
			
			//3. SQL문 결정(객체화)
			String sql = "select * from "+table+" where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, inputnum);
			System.out.println("SQL문 객체화 OK");
			//4. SQL문 전송
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new BankDTO();
				String id =rs.getString(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String tel = rs.getString(4);
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
				
				
			}else {
				System.out.println("검색 결과가 없습니다.");
			}
			
			System.out.println("SQL문 전송 OK");
			
		} catch (Exception e) {
			System.out.println("DB 처리중 에러발생....");
			System.out.println(e.getMessage());
		}
		finally {
				//에러 발생 여부와 상관없이 무조건 실행시켜야 하는 코드.
			//5. SQL문의 결과가 있으면 받아서 처리
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("자운 해제 중 에러 발생!!");
			}
		}//try-catch finally
		return dto;
		
	}//select
}

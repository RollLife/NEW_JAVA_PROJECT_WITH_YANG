package test1;
 
import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
 
public class TestComnet {
 
	public static void main(String[] args) throws FileNotFoundException{
		//mysql jar파일은 http://dev.mysql.com/downloads/connector/j/ 여기서
		//추가 방법은 test 오른쪽 클릭 -build Path - configure build Path - Libraries - Add External Jars 해서 다운받은거 추가
		//scanner.useDelimiter(",");
		String url = "jdbc:mysql://localhost:3306/hanyangs";
		String user = "root";
		String password ="apmsetup";
		
		File csv = new File("c:\\Users\\comnet\\Downloads\\nyam.csv");
		
//		Comnet[] c= new Comnet[5];
//		c[0] = new Comnet("안병호",173,74);
//		c[1] = new Comnet("박창율",172,60);
//		c[2] = new Comnet("원치영",176,69);
//		c[3] = new Comnet("안정우",172,80);
//		c[4] = new Comnet("장재현",172,77);
		Connection myCon = null;
		BufferedReader br = new BufferedReader(new FileReader(csv));
		try {
			Scanner scanner = new Scanner(br);
			Class.forName("com.mysql.jdbc.Driver");
			myCon = DriverManager.getConnection(url,user,password);
			String query = "insert into comnet(name,height,weight) values (?,?,?)";
			PreparedStatement pstmt = myCon.prepareStatement(query); //외우기 ☆★☆	
//			for(Comnet hanyang : c){
//				pstmt.setString(1, hanyang.getName());
//				pstmt.setInt(2, hanyang.getHeight());
//				pstmt.setInt(3, hanyang.getWeight());		
//			}
			while(scanner.hasNext()){
				String data = scanner.nextLine();
				String[] str = data.split(",");
				String name = str[0];
				int height = Integer.parseInt(str[1]); // 형변환 parseInt
				int weight = Integer.parseInt(str[2]); // 형변환을 해주는 이유는 String형 scanner(data)를 받아주었으니 그것을 Int형으로 바꿔주는 것이다.
				// Integer.parseInt(매개변수) 는  명시적 -> 데이터타입 String만 지원 / (int)(str[1]) 은 명시적 형변환  ->Byte,short,long 만 지원하기에
				// 현재 문서엔 적합하지않는 형변환이다.
				
				pstmt.setString(1, name);
				pstmt.setInt(2, height);
				pstmt.setInt(3, weight);
				pstmt.execute();
				System.out.println(data);
			}
			pstmt.close();
			myCon.close();
		}catch(Exception e){
			e.printStackTrace();//오류 출력
		}
		System.out.println("완료염"); //database 에 insert 를 성공적으로 입력했다는것을 알려주기 위함
		
	} // DataBase : hanyang / Table : comnet / name varchar(50),height int(3), weight(3)
 
}

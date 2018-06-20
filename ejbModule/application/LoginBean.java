package application;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class LoginBean implements LoginBeanRemote {
    
	// sql driver doesnt work in ejb, hardcoding logins
	// returns userId loosely related to db
	public int login(String username, String password) {
		if(username.equals("wilma") && password.equals("arugula"))
			return 1;
		if(username.equals("leroy") && password.equals("wipeout"))
			return 2;
		if(username.equals("sasquatch") && password.equals("jerky"))
			return 3;
		
		return -1;
		
		/*String hashed = hashPassword(password);

		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.createConnection();
			
			ps = conn.prepareStatement("select * from user u inner join role r on (u.role_id = r.id) where username = ? and password = ? limit 1");
			ps.setString(1, username);
			ps.setString(2, hashed);
			
			ResultSet rs = ps.executeQuery();

			Map<String, Object> data = new HashMap<>();
		    ResultSetMetaData meta = rs.getMetaData();
		    while (rs.next()) {
		        for (int i = 1; i <= meta.getColumnCount(); i++) {
		            String key = meta.getColumnName(i);
		            String value = rs.getString(key);
		            data.put(key, value);
		        }
		    }
		    return data;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();
				
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;*/
	}
	
	private String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(encodedHash);
		} catch(Exception e) {
			return null;
		}
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    
		    if(hex.length() == 1)
		    		hexString.append('0');
		    
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
}
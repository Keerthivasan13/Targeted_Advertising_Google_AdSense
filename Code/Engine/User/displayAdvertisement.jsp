<html>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 

<%
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projdb", "root", "");
ResultSet rs = null;
PreparedStatement psmnt = null;
InputStream sImage;
try {
psmnt = con.prepareStatement("SELECT image FROM advertisement WHERE Id = ?");
psmnt.setInt(1,235); 
rs = psmnt.executeQuery();
if(rs.next()) {
byte[] bytearray = new byte[1048576];
int size=0;
sImage = rs.getBinaryStream(1);
response.reset();
response.setContentType("image/jpeg");
while((size=sImage.read(bytearray))!= -1 ){
response.getOutputStream().write(bytearray,0,size);
}
}
}
catch(Exception ex){
out.println("error :"+ex);
}
finally {
psmnt.close();
con.close();
}
%>
</html>
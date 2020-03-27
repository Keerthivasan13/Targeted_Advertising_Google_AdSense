package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.io.*;

public final class getimg_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html>\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");

Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projdb", "root", "");
ResultSet rs = null;
PreparedStatement psmnt = null;
InputStream sImage;
try {
psmnt = con.prepareStatement("SELECT image FROM advertisement WHERE Id = ? or Id = ?");
psmnt.setInt(1,242); 
psmnt.setInt(2,242);
rs = psmnt.executeQuery();
response.reset();
response.setContentType("image/jpeg");
while(rs.next()) {
byte[] bytearray = new byte[1048576];
int size=0;
sImage = rs.getBinaryStream(1);
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

      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

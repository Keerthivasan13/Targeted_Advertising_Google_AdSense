package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.URLConnection;
import java.net.URL;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

public final class rank_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Ranker</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
 
class UrlNotFoundException extends Exception
{
  public UrlNotFoundException(String message)
  {
    super(message);
  }
}
class DatabaseException extends Exception
{
  public DatabaseException(String message)
  {
    super(message);
  }
}
class Ad
{
    public int Id;
    public float CPC;
    public float CPM;
    public int Clicks;
    public int Impressions;
    public int Premium;
    public Ad(int a,float b,float c,int d,int e,int f)
    {
        Id=a;
        CPC=b;
        CPM=c;
        Clicks=d;
        Impressions=e;
        Premium=f;
    }
}            
    try{
        int no_of_ads=0;
        int page_id=0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
        String req=request.getParameter("url");
        
        String ip=request.getParameter("ip");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select No_Of_Ads,Id from webpage where Page_URL like '%"+req+"%'");
        if(rs.next()!=false)
        {
            no_of_ads=rs.getInt("No_Of_Ads");
            page_id=rs.getInt("Id");
        }
        else
        {
            throw new UrlNotFoundException("URL Not Registered with Us. You are Forbidden to Access.");
        }
    URL url=new URL("http://ip-api.com/json/"+ip);
    URLConnection conn = url.openConnection();
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String json=br.readLine();
    String s[]=json.split("\"country\":\"");
    String s1[]=s[1].split("\",\"");
    String location=s1[0].toLowerCase(); 
    st=con.createStatement();
    rs=st.executeQuery("select distinct(Id) from Advertisement where Category in (select AdCategory from adpagemapping where PageCategory in (select Category from webpage where Id = "+page_id+"))  or Category in (select Id from adcategory where Parent_Id in (select AdCategory from adpagemapping where PageCategory in (select Category from webpage where Id = "+page_id+")))and location like '"+location+"'");
    ArrayList<Integer> adlist = new ArrayList<Integer>();
    while(rs.next()==true)
    {
        adlist.add(rs.getInt("Id"));
    }  
    Ad ad;
    ArrayList<Ad> ads= new ArrayList<Ad>();
    if(adlist.size()>2*no_of_ads)
    {
        for(Integer i:adlist)
        {
            rs=st.executeQuery("Select a.Advertisement_Id,a.CPC,a.CPM,a.Clicks,a.Impressions,Premium_Membership from adcost a, advertisement b,advertiser c where a.Advertisement_Id=b.Id and b.Advertiser_Id = c.Id and b.Id = "+i);
            rs.next();
            ad=new Ad(rs.getInt(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            ads.add(ad);
        }   
    }
    else
    {
        rs=st.executeQuery("select distinct(Id)from Advertisement where Category in (select AdCategory from adpagemapping where PageCategory in (select Category from webpage where Id = "+page_id+" ),(select Id from adcategory where Parent_Id in (select AdCategory from adpagemapping where PageCategory in (select Category from webpage where Id = "+page_id+" )))) and location like 'global' or location like '"+location+"'");
        while(rs.next()==true)
        {
            adlist.add(rs.getInt("Id"));
        }
        for(Integer i:adlist)
        {
            rs=st.executeQuery("Select a.Advertisement_Id,a.CPC,a.CPM,a.Clicks,a.Impressions,c.Premium_Membership from adcost a, advertisement b,advertiser c where a.Advertisement_Id=b.Id and b.Advertiser_Id = c.Id and b.Id = "+i);
            rs.next();
           ad=new Ad(rs.getInt(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            ads.add(ad);
        }   
    }
    
//    for(Ad h: ads)
//    {
//        response.getWriter().print("Id="+h.Id+" "+h.Clicks+" "+h.Impressions+" "+h.CPM+" "+h.CPC+" "+h.Premium);
//    }
    //ads have the to be ranked ads
    Collections.sort(ads, new Comparator<Ad>() {
        @Override
        public int compare(Ad a, Ad b)
        {

            return  (((a.Clicks/a.CPC +a.Impressions/a.CPM)*(a.Premium+0.5))<((b.Clicks/b.CPC +b.Impressions/b.CPM)*(b.Premium+0.5)))?-1:1;
        }
    });
//    for(Ad h: ads)
//    {
//        response.getWriter().print("Id="+h.Id+" "+h.Clicks+" "+h.Impressions+" "+h.CPM+" "+h.CPC+" "+h.Premium);
//    }
    
    for(int i=0;i<2*no_of_ads;i++)
    {
        rs=st.executeQuery("SELECT Keywords from advertisement where Id="+ads.get(i).Id);
        rs.next();
        response.getWriter().println("Keyword :"+rs.getString(1));
        st.executeUpdate("update adcost set Impressions="+ (ads.get(i).Impressions+1)+" where Advertisement_Id="+ads.get(i).Id);
    }
    
    
    }
    catch (Exception ex) {
            out.println(ex);
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

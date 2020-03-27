<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="weka.classifiers.bayes.NaiveBayes" %>
<%@ page import="weka.core.Instances" %>
<%@ page import="weka.experiment.InstanceQuery" %>
<%@ page import="weka.core.Instance"%>
<%@page import=" classifier.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adding Advertisement </title>
</head>
<body>
<%
PrintWriter writer=response.getWriter();
try
{
int count=0;    int sc=0;
String adname=request.getParameter("adname");
String location=request.getParameter("location");
String keyword=request.getParameter("key");
int cpm=Integer.parseInt(request.getParameter("cpm"));
String pcat=request.getParameter("pcat");
int cpc=Integer.parseInt(request.getParameter("cpc"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");

    request.getSession(false);
    int id= (Integer)session.getAttribute("id");

ArrayList<String> list= new ArrayList<String>();
StringTokenizer str= new StringTokenizer(keyword,"+");
while(str.hasMoreTokens())
{
    list.add(str.nextToken());
}

    Instances AdKeywordRecords;
    NaiveBayes AdKeywordModel;
    InstanceQuery query;
    try{
    query = new InstanceQuery();
    query.setUsername("root");
    query.setPassword("");
    query.setQuery("select c.Name as PC,b.Name as SC ,Keyword from adkeyword a,adcategory b,adcategory c where a.Category=b.Id and b.Parent_Id=c.Id and c.Parent_Id=0");
    AdKeywordRecords= query.retrieveInstances();
    AdKeywordRecords.setClassIndex(AdKeywordRecords.numAttributes()-2);
    AdKeywordModel= new NaiveBayes();
    AdKeywordModel.buildClassifier(AdKeywordRecords);
    Classifier classifier = new Classifier();
    String scat=classifier.classifyInstance(AdKeywordModel,AdKeywordRecords,list,pcat);
    Statement sta=con.createStatement();
    ResultSet rs=sta.executeQuery("Select Id from adcategory where Name like '"+scat+"'");

    if(rs.next())
     sc=(rs.getInt(1));
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
  
PreparedStatement st=con.prepareStatement("insert into advertisement(Keywords,Category,Location,Advertiser_Id) values(?,?,?,?)");
st.setString(1,keyword);
st.setInt(2,sc);
st.setString(3,location);
st.setInt(4,id);
count=st.executeUpdate();
writer.println("Advertisement Added in our Database. We will update you further details.");


}
catch(Exception e)
{
	writer.println("Exception is : "+e);
}
%>
    
</body>
</html>
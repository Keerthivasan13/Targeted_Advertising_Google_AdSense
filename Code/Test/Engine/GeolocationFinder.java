/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keerthi
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Geo {
    public static void main(String args[])
    {
        try {
            URL url = new URL("https://api.ipify.org?");
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
            String ip=br.readLine();
            System.out.println(ip);
            
            url=new URL("https://freegeoip.net/xml/"+ip);
            conn = url.openConnection();
            
            br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                    System.out.println(inputLine);
            }
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Geo.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}

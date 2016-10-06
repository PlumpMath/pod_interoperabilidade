package ifpb.pod.q12.node2;

/**
 * Created by Natarajan Rodrigues on 06/10/16.
 */


import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@SuppressWarnings("serial")
public class Op2ServletImpl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        GlobalVariable variable = GlobalVariable.instance();
        resp.setContentType("text/plain");

        resp.setStatus(200);
        String s = "OK - " + variable.toString();
        resp.getWriter().append(s);
        resp.setContentLength(s.length());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //
        GlobalVariable variable = GlobalVariable.instance();
//        synchronized (variable) {
        variable.incrOp2();
        resp.getWriter().append(variable.toString());
        updateOp2OnNode3();
//        }
        //
        resp.setContentType("plain/text");
        resp.setContentLength(2);
        resp.setStatus(200);
    }

    private void updateOp2OnNode3() throws IOException {

        Thread t = new Thread(){
            public void run(){

                int responseCode = 0;
                do {
                    String url = "http://podq12node3.appspot.com/op2/update";
                    URL obj = null;

                    try {
                        obj = new URL(url);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("POST");
                        // Send post request
                        con.setDoOutput(true);
                        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                        wr.flush();
                        wr.close();

                        responseCode = con.getResponseCode();
                        System.out.println("\nSending 'POST' request to URL : " + url);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (responseCode != 200);

            }
        };

        t.start();

    }

}


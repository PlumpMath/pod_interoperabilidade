package ifpb.pod.q12.node3.server;

/**
 * Created by Natarajan Rodrigues on 06/10/16.
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Op1ServletImpl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        GlobalVariable variable = GlobalVariable.instance();
        resp.setContentType("text/plain");
        resp.setContentLength(2);
        resp.setStatus(200);
        resp.getWriter().append("OK - " + variable.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //
        GlobalVariable variable = GlobalVariable.instance();
        synchronized (variable) {
            variable.incrOp1();
            resp.getWriter().append(variable.toString());
            System.out.println("Atualizando op1 em node 2");
            updateOp1OnNode2();
        }

        resp.setContentType("plain/text");
        resp.setContentLength(2);
        resp.setStatus(200);

    }


    private void updateOp1OnNode2() throws IOException {
        String url = "http://q12node2.herokuapp.com/op1/update";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("POST / HTTP/1.1\r\n");
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);

    }

}


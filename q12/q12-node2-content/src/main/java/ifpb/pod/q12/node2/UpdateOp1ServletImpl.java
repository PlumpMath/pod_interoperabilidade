package ifpb.pod.q12.node2;

/**
 * Created by Natarajan Rodrigues on 06/10/16.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class UpdateOp1ServletImpl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setContentLength(2);
        resp.setStatus(200);
        resp.getWriter().append("OK");
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
        }
        //
        resp.setContentType("plain/text");
        resp.setContentLength(variable.toString().length());
        resp.setStatus(200);
    }

}

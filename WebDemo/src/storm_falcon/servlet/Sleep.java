package storm_falcon.servlet;

import java.io.IOException;

public class Sleep extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String millis = request.getParameter("millis");
        try {
            Thread.sleep(Long.parseLong(millis));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}

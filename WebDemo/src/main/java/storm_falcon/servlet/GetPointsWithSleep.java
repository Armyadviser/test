package storm_falcon.servlet;

import storm_falcon.Location;
import storm_falcon.LocationUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetPointsWithSleep extends javax.servlet.http.HttpServlet {

    private List<Location> points;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String sIndex = request.getParameter("index");
        String sSize = request.getParameter("size");
        int index = Integer.valueOf(sIndex);
        int size = Integer.valueOf(sSize);

        String resp;
        if (index + size < points.size()) {
            List<Location> locations = points.subList(index, index + size);
            resp = LocationUtils.locations2Json(locations.stream());
        } else {
            resp = "[]";
        }

        PrintWriter out = response.getWriter();
        out.print(resp);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    public void init() {
        String filePath = "D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt";
        try {
            points = LocationUtils.loadLocations(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

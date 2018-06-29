package storm_falcon.servlet;

import storm_falcon.Location;
import storm_falcon.LocationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class GetSpecifiedPoints extends HttpServlet {

    private List<Location> locations;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Set<String> nearBys = new HashSet<>();
        nearBys.add("63902098");

        nearBys.add("64307280");
        nearBys.add("64313758");
        nearBys.add("64307863");
//        nearBys.add("63903211");

        Stream<Location> locationStream = locations.stream()
                .filter(location -> nearBys.contains(location.id));

        String json = LocationUtils.locations2Json(locationStream);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void init() {
        try {
            locations = LocationUtils.loadLocations("D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

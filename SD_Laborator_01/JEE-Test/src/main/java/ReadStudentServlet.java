import beans.RootBean;
import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ReadStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        File file = new File("/home/e/Downloads/labsd01/SD_Laborator_01/JEE-Test/student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();
        RootBean root = xmlMapper.readValue(file, RootBean.class);
        if (root != null) {
            request.setAttribute("lista", root);
            request.getRequestDispatcher("./info-student.jsp").forward(request, response);
        } else {
            response.sendError(404, "Nu este niciun student in fisier!");
            return;
        }
    }
}

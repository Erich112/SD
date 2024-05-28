import beans.RootBean;
import com.ctc.wstx.shaded.msv_core.writer.relaxng.Context;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteStudentiServlet extends HttpServlet {
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File file = new File("/home/e/Downloads/labsd01/SD_Laborator_01/JEE-Test/student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();
        RootBean root = xmlMapper.readValue(file, RootBean.class);
        if (root.getList().isEmpty()) {
            response.getWriter().print("Nu este niciun student in fisier!");

        } else {
            request.setAttribute("lista", root);
            xmlMapper.writeValue(file, root);
            request.getRequestDispatcher("./info-student.jsp").forward(request, response);
        }

    }
}
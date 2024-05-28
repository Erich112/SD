import beans.RootBean;
import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));

        /*
        procesarea datelor - calcularea anului nasterii
         */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

        // initializare serializator Jackson
        XmlMapper mapper = new XmlMapper();

        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setAnNastere(anNastere);

        // serializare bean sub forma de string XML
        File file = new File("/home/e/Downloads/labsd01/SD_Laborator_01/JEE-Test/student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun fisier de studenti serializat pe disc!");
            return;
        }
        RootBean root = mapper.readValue(file, RootBean.class);
        root.getList().add(bean);
        mapper.writeValue(file, root);

        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
        request.setAttribute("lista", root);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}
package beans;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;

public class RootBean implements java.io.Serializable {
    @JacksonXmlProperty(localName = "StudentBean")
    @JacksonXmlElementWrapper(useWrapping = false)
    private ArrayList<StudentBean> list = null;
    public ArrayList<StudentBean> getList() {
        return list;
    }

    public void setList(ArrayList<StudentBean> list) {
        this.list = list;
    }

    public RootBean() {
    }

}

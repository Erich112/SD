package beans;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StudentBean implements java.io.Serializable {
    @JacksonXmlProperty
    private String nume = null;
    @JacksonXmlProperty
    private String prenume = null;
    @JacksonXmlProperty
    private int varsta = 0;
    @JacksonXmlProperty
    private int anNastere = 0;

    public StudentBean() {
    }

    public int getAnNastere() {
        return anNastere;
    }

    public void setAnNastere(int anNastere) {
        this.anNastere = anNastere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}
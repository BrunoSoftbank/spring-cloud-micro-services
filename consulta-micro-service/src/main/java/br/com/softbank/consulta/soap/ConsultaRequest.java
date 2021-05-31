
package br.com.softbank.consulta.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuarioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="exameId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="laboratorioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaRequest", propOrder = {
    "usuarioId",
    "exameId",
    "laboratorioId"
})
public class ConsultaRequest {

    protected int usuarioId;
    protected int exameId;
    protected int laboratorioId;

    /**
     * Gets the value of the usuarioId property.
     * 
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * Sets the value of the usuarioId property.
     * 
     */
    public void setUsuarioId(int value) {
        this.usuarioId = value;
    }

    /**
     * Gets the value of the exameId property.
     * 
     */
    public int getExameId() {
        return exameId;
    }

    /**
     * Sets the value of the exameId property.
     * 
     */
    public void setExameId(int value) {
        this.exameId = value;
    }

    /**
     * Gets the value of the laboratorioId property.
     * 
     */
    public int getLaboratorioId() {
        return laboratorioId;
    }

    /**
     * Sets the value of the laboratorioId property.
     * 
     */
    public void setLaboratorioId(int value) {
        this.laboratorioId = value;
    }

}

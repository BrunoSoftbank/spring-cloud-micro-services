//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.12 at 01:09:34 AM BRT 
//


package br.com.softbank.consultawebservice.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "ConsultaResponse", propOrder = {
    "id",
    "usuarioId",
    "exameId",
    "laboratorioId"
})
public class ConsultaResponse {

    protected int id;
    protected int usuarioId;
    protected int exameId;
    protected int laboratorioId;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

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
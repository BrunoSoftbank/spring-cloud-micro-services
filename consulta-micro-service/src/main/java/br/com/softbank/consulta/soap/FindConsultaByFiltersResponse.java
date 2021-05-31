
package br.com.softbank.consulta.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaResponse" type="{http://softbank.com.br}ConsultaResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consultaResponse"
})
@XmlRootElement(name = "FindConsultaByFiltersResponse")
public class FindConsultaByFiltersResponse {

    @XmlElement(required = true)
    protected ConsultaResponse consultaResponse;

    /**
     * Gets the value of the consultaResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaResponse }
     *     
     */
    public ConsultaResponse getConsultaResponse() {
        return consultaResponse;
    }

    /**
     * Sets the value of the consultaResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaResponse }
     *     
     */
    public void setConsultaResponse(ConsultaResponse value) {
        this.consultaResponse = value;
    }

}

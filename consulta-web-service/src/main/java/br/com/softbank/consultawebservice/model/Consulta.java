package br.com.softbank.consultawebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    private int id;
    private int usuarioId;
    private int exameId;
    private int laboratorioId;
       
 }

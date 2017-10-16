/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistance;

import edu.eci.pdsw.samples.entities.Paciente;
import java.util.List;

/**
 *
 * @author sebas
 */
public interface PacienteDAO {
    
    public List<Paciente> loadAll();
    
    public Paciente load();
    
    public Paciente loadByID();
    
    public void save();
    
    public void update();
    
}

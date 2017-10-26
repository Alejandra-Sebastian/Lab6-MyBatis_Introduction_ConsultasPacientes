/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistance.mybatis;

import edu.eci.pdsw.persistance.PacienteDAO;
import edu.eci.pdsw.samples.entities.Paciente;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author sebas
 */
public class PacienteDAOMyBATIS implements PacienteDAO{

    @Override
    public List<Paciente> loadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente loadByID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public void update(Paciente p) throws PersistenceException {
        try{
            pacienteMapper.actualizarPaciente(p);
             //el resto de la implementación
        }   
        catch(Exception e){
            throw new PersistenceException("Error al actualizar el paciente "+idPaciente,e);
        }
        
    }
    
}

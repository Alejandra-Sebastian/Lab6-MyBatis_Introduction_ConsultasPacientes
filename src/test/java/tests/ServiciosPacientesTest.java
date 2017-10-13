/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosHistorialPacientesFactory;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
/*
Clases de Equivalencias:
CE1: Que la consulta sea para un paciente ya registrado
CE2: Que el id del paciente sea un id valido
CE3: Que el tipo de id sea un id valido
CE4: Que la consulta sea una consulta valida
*/
public class ServiciosPacientesTest {
    
    private ServiciosPacientes servicepacientes;
    
    @Before
    public void setUp() {
        servicepacientes = ServiciosHistorialPacientesFactory.getInstance().getServiciosPaciente();
    }
    
    @Test
    public void ElPacienteNoEstaRegistrado() {
        Consulta consulta = new Consulta(new Date(21,9,2017,10,21), "Es una consulta", 15000);
        try {
            servicepacientes.agregarConsultaPaciente(0, "numerico", consulta);
        } catch (ExcepcionServiciosPacientes ex) {
            assertEquals("Paciente 0 no esta registrado", ex.getMessage());
        }
    }
    
    @Test
    public void RegistroDePaciente(){       
        int id = 1234;
        String tipoid= "TI";
        String nombre="Juanchito"; 
        Date fN = new Date(23,4,2001);
        Eps eps = new Eps("famisanar","54321");
        Paciente paciente = new Paciente(id, tipoid,nombre,fN,eps);
        boolean b = false;
        try {
            servicepacientes.registrarNuevoPaciente(paciente);
            b = servicepacientes.consultarPacientes().contains(paciente);
        } catch (ExcepcionServiciosPacientes ex) {           
        } 
        assertTrue(b);
    }
    
    @Test
    public void consultarPacienteTest() {
        Paciente paciente = new Paciente(1, "CC", "Luis", new Date(1998, 01, 07), new Eps("Sanitas", "13215648-45"));
        try {
            servicepacientes.registrarNuevoPaciente(paciente);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Paciente p = null;
        try {
            p = servicepacientes.consultarPaciente(1, "CC");
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(paciente, p);        
    }
    
    @Test
    public void obtenerConsultasEpsTest() {
        Paciente paciente = new Paciente(1, "CC", "Luis", new Date(1998, 01, 07), new Eps("Sanitas", "13215648-45"));
        List<Consulta> consultas = null;
        try {
            servicepacientes.registrarNuevoPaciente(paciente);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consulta c = new Consulta(new Date(2017, 8, 29, 12, 00), "Dolor de Cabeza", 15000);
        try {
            servicepacientes.agregarConsultaPaciente(1, "CC", c);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultas = servicepacientes.obtenerConsultasEps("Sanitas");
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(2,consultas.size());        
    }
    
    @Test
    public void obtenerEpsRegistradas() {
        List<Eps> listaEps = null;
        try {
            listaEps = servicepacientes.obtenerEPSsRegistradas();
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ServiciosPacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(6, listaEps.size());
    }
}

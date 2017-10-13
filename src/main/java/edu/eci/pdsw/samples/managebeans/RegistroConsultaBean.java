/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managebeans;


import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosHistorialPacientesFactory;
import edu.eci.pdsw.samples.services.ServiciosPacientes;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "HistorialPacientes")
@SessionScoped
public class RegistroConsultaBean implements Serializable {

    private final ServiciosPacientes servicepacientes = ServiciosHistorialPacientesFactory.getInstance().getServiciosPaciente();
    private int id;
    private String tipoid;
    private String nombre;
    private Date fechaNacimiento;
    private Eps eps;
    private String nombreEps;
    private Paciente paciente;
    private Consulta consulta;
    private List<Eps> listaEps;
    private List<String> nombresEps;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private Paciente pacienteSeleccionado;
    private Date fechaYHora;
    private String resumen;
    private long costo;
    private String consultaEps;
    private List<Integer> IDConsultas;

    

    public RegistroConsultaBean(){
        try {
            listaEps = servicepacientes.obtenerEPSsRegistradas();
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombresEps = new ArrayList<String>();
        for (Iterator i = listaEps.iterator(); i.hasNext();) {
            Eps e = (Eps) i.next();
            nombresEps.add(e.getNombre());
        }
        
    }

    public void showMessage(String estado, String mensaje) {
        FacesMessage message;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, estado, mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void agregarPaciente() {
        for(Eps e: listaEps) {
            if(e.getNombre().equals(nombreEps)) {
                eps = e;
            }
        }
        paciente = new Paciente(id, tipoid, nombre, fechaNacimiento, eps);
        try {
            servicepacientes.registrarNuevoPaciente(paciente);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void agregarConsulta() {
        consulta = new Consulta(fechaYHora, resumen, costo);
        try {
            servicepacientes.agregarConsultaPaciente(pacienteSeleccionado.getId(), pacienteSeleccionado.getTipoId(), consulta);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTipoid() {
        return tipoid;
    }
    
    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento; 
    }
    
    public Eps getEps() {
        return eps;
    }
    
    public void setEps(Eps eps) {
        this.eps = eps;
    }
    
    public String getNombreEps() {
        return nombreEps;
    }
    
    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }
    
    public List<String> getNombresEps() {
        return nombresEps;
    }
    
    public void setNombresEps(List<String> nombresEps) {
        this.nombresEps = nombresEps;
    }
    
    public List<Eps> getListaEps() {
        
        return listaEps;
    }
    
    public void setListaEps(List<Eps> listaEps) {
        this.listaEps = listaEps;
    }
    
    public List<Paciente> getPacientes() {
        try {
            pacientes = servicepacientes.consultarPacientes();
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacientes;
    }
    
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    public Paciente getPacienteSeleccionado(){
        return pacienteSeleccionado;
    }
    
    public void setPacienteSeleccionado(Paciente pa){
        pacienteSeleccionado = pa;
    }
    
    public Date getFechaYHora() {
        return fechaYHora;
    }
    
    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
    
    public String getResumen() {
        return resumen;
    }
    
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
    public long getCosto() {
        return costo;
    }
    
    public void setCosto(long costo) {
        this.costo = costo;
    }
    
    public List<Consulta> getConsultas() {
        Object[] c = pacienteSeleccionado.getConsultas().toArray();
        consultas = new ArrayList<Consulta>();
        for(Object o : c) {
            consultas.add((Consulta) o);
        }
        return consultas;
    }
    
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    public String getConsultaEps() {
        return consultaEps;
    }
    
    public void setConsultaEps(String consultaEps) {
        this.consultaEps = consultaEps;
    }
}

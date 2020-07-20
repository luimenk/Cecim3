package com.demo.model.operacion;

import com.demo.model.Client;
import javax.persistence.*;

@Entity
public class SolicitudServicioCliente {

    @Id
    @GeneratedValue
    @Column(name = "Solicitud_Servicio_Cliente_Id", nullable = false)
    private Long solicitudServicioClienteId;

    @Column(name = "Folio_Solicitud_Servicio_Cliente", length = 250, nullable = false)
    private String folioSolitudServicioCliente;

    @Column(name = "Servicio_Urgente", length = 250, nullable = false)
    private String servicioUrgente;

    @Column(name = "Fecha_Envio_Muestras", length = 250, nullable = false)
    private String fechaEnvioMuestras;

    @Column(name = "Fecha_Pago", length = 250, nullable = false)
    private String fechaPago;

    @Column(name = "Nombre_Firma_Emisor", length = 250, nullable = false)
    private String nombreFirmaEmisor;

    @Column(name = "Almacenamiento_Especial", length = 250, nullable = false)
    private String almacenamientoEspecial;

    @Column(name = "Especifique", length = 250, nullable = false)
    private String especifique;

    @Column(name = "Fecha_Recepcion_Muestras", length = 250, nullable = false)
    private String fechaRecepcionMuestras;

    @Column(name = "Fecha_Compromiso_Entrega", length = 250, nullable = false)
    private String fechaCompromisoEntrega;

    @Column(name = "Nombre_Firma_Receptor", length = 250, nullable = false)
    private String nombreFirmaReceptor;

    @Column(name = "Nombre_Firma_Calidad", length = 250, nullable = false)
    private String nombreFirmaCalidad;

    @Column(name = "Devolucion_Muestras", length = 250, nullable = false)
    private String devolucionMuestras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Client_Id")
    private Client client;

    public SolicitudServicioCliente() {
    }

    public SolicitudServicioCliente(Long solicitudServicioClienteId, String folioSolitudServicioCliente, String servicioUrgente, String fechaEnvioMuestras, String fechaPago, String nombreFirmaEmisor, String almacenamientoEspecial, String especifique, String fechaRecepcionMuestras, String fechaCompromisoEntrega, String nombreFirmaReceptor, String nombreFirmaCalidad, String devolucionMuestras, Client client) {
        this.solicitudServicioClienteId = solicitudServicioClienteId;
        this.folioSolitudServicioCliente = folioSolitudServicioCliente;
        this.servicioUrgente = servicioUrgente;
        this.fechaEnvioMuestras = fechaEnvioMuestras;
        this.fechaPago = fechaPago;
        this.nombreFirmaEmisor = nombreFirmaEmisor;
        this.almacenamientoEspecial = almacenamientoEspecial;
        this.especifique = especifique;
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
        this.fechaCompromisoEntrega = fechaCompromisoEntrega;
        this.nombreFirmaReceptor = nombreFirmaReceptor;
        this.nombreFirmaCalidad = nombreFirmaCalidad;
        this.devolucionMuestras = devolucionMuestras;
        this.client = client;
    }

    public Long getSolicitudServicioClienteId() {
        return solicitudServicioClienteId;
    }

    public void setSolicitudServicioClienteId(Long solicitudServicioClienteId) {
        this.solicitudServicioClienteId = solicitudServicioClienteId;
    }

    public String getFolioSolitudServicioCliente() {
        return folioSolitudServicioCliente;
    }

    public void setFolioSolitudServicioCliente(String folioSolitudServicioCliente) {
        this.folioSolitudServicioCliente = folioSolitudServicioCliente;
    }

    public String getServicioUrgente() {
        return servicioUrgente;
    }

    public void setServicioUrgente(String servicioUrgente) {
        this.servicioUrgente = servicioUrgente;
    }

    public String getFechaEnvioMuestras() {
        return fechaEnvioMuestras;
    }

    public void setFechaEnvioMuestras(String fechaEnvioMuestras) {
        this.fechaEnvioMuestras = fechaEnvioMuestras;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNombreFirmaEmisor() {
        return nombreFirmaEmisor;
    }

    public void setNombreFirmaEmisor(String nombreFirmaEmisor) {
        this.nombreFirmaEmisor = nombreFirmaEmisor;
    }

    public String getAlmacenamientoEspecial() {
        return almacenamientoEspecial;
    }

    public void setAlmacenamientoEspecial(String almacenamientoEspecial) {
        this.almacenamientoEspecial = almacenamientoEspecial;
    }

    public String getEspecifique() {
        return especifique;
    }

    public void setEspecifique(String especifique) {
        this.especifique = especifique;
    }

    public String getFechaRecepcionMuestras() {
        return fechaRecepcionMuestras;
    }

    public void setFechaRecepcionMuestras(String fechaRecepcionMuestras) {
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
    }

    public String getFechaCompromisoEntrega() {
        return fechaCompromisoEntrega;
    }

    public void setFechaCompromisoEntrega(String fechaCompromisoEntrega) {
        this.fechaCompromisoEntrega = fechaCompromisoEntrega;
    }

    public String getNombreFirmaReceptor() {
        return nombreFirmaReceptor;
    }

    public void setNombreFirmaReceptor(String nombreFirmaReceptor) {
        this.nombreFirmaReceptor = nombreFirmaReceptor;
    }

    public String getNombreFirmaCalidad() {
        return nombreFirmaCalidad;
    }

    public void setNombreFirmaCalidad(String nombreFirmaCalidad) {
        this.nombreFirmaCalidad = nombreFirmaCalidad;
    }

    public String getDevolucionMuestras() {
        return devolucionMuestras;
    }

    public void setDevolucionMuestras(String devolucionMuestras) {
        this.devolucionMuestras = devolucionMuestras;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "SolicitudServicioCliente{" +
                "solicitudServicioClienteId=" + solicitudServicioClienteId +
                ", folioSolitudServicioCliente='" + folioSolitudServicioCliente + '\'' +
                ", servicioUrgente='" + servicioUrgente + '\'' +
                ", fechaEnvioMuestras='" + fechaEnvioMuestras + '\'' +
                ", fechaPago='" + fechaPago + '\'' +
                ", nombreFirmaEmisor='" + nombreFirmaEmisor + '\'' +
                ", almacenamientoEspecial='" + almacenamientoEspecial + '\'' +
                ", especifique='" + especifique + '\'' +
                ", fechaRecepcionMuestras='" + fechaRecepcionMuestras + '\'' +
                ", fechaCompromisoEntrega='" + fechaCompromisoEntrega + '\'' +
                ", nombreFirmaReceptor='" + nombreFirmaReceptor + '\'' +
                ", nombreFirmaCalidad='" + nombreFirmaCalidad + '\'' +
                ", devolucionMuestras='" + devolucionMuestras + '\'' +
                ", client=" + client +
                '}';
    }
}
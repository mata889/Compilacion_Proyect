package main;
/**
 *
 * @author Ricardo
 */
public class variables {
    public String tipo;
    public String id;
    public String ambito;
    public String offset;


    public variables(String tipo, String id, String ambito) {
        this.tipo = tipo;
        this.id = id;
        this.ambito = ambito;
    }
    
    //IGNOREN ESTE POR AHORA, ES PARA CUANDO USEMOS ASSEMBLER
    public variables(String tipo, String id, String ambito, String offset) {
        this.tipo = tipo;
        this.id = id;
        this.ambito = ambito;
        this.offset = offset;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
    
    
}

package edu.udg.core.data.model;

import java.io.Serializable;


public class Message implements Serializable{
    
    private String message;
    private Boolean status;
    private Object object;
    
    public Message(){
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
    
}

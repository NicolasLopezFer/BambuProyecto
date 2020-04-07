package com.panda.bambu.exception;

public class UsernameOrIdNotFound  extends Exception {

    
    /**
     *
     */
    private static final long serialVersionUID = 8092019068562867376L;

    public UsernameOrIdNotFound() {
          super("Usuario o Id no encontrado");
    }
        
    public UsernameOrIdNotFound(String message) {
           super(message);
    }

}
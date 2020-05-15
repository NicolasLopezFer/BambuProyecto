package com.panda.bambu.model.metodo_pago;

public enum MetodoPago
{
    EFECTIVO("Efectivo"),
    CHEQUE("Cheque"),
    TARJETACREDITO("Tarjeta de Credito"),
    TARJETADEBITO("Tarjeta de Debito"),
    TRANSFERENCIA("Transferencia"),
    MONEDAVIRTUAL("Moneda Virtual"),
    ONLINE("Online"); 
	
	private String metodoPago;
    
    private MetodoPago (String metodoPago){
		this.metodoPago = metodoPago;
		
	}
}
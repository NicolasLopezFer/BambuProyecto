package com.panda.bambu.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.User;


@Entity 

public class Company{

	@Id
    @NotNull(message="nit is compulsory")
    @Column(name="nit")
    private Long nit;

    @NotNull(message="name is compulsory")
    @Column(name="nombre")
    private String name;

    //@NotNull(message="social_reason is compulsory")
    @Column(name="razon_social")
    private String social_reason;

    //@NotNull(message="direction is compulsory")
    @Column(name="direccion")
    private String direction;

    //@NotNull(message="telephone is compulsory")
    @Column(name="telefono")
    private Long telephone;

    //@NotNull(message="famiEmpresa_id is compulsory")
    @Column(name="auth_user_id")
    private Double famiEmpresa_id;

    /*@ManyToOne()
    @JoinColumn(name="usuario")
    private User user;*/
    




 public Company(Long nit, String name, String social_reason, String direction, Long telephone) {//, double famiEmpresa_id ){
         
         this.nit = nit;
         this.name = name;
         this.social_reason = social_reason;
         this.direction=direction;
         this.telephone=telephone;
         //this.famiEmpresa_id=famiEmpresa_id;
         

    }
    
 public Company(){
     
 }


    public Long getNit(){
        return nit;
    }
    public void setNit(Long nit){
         this.nit = nit;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
         this.name = name;
    }


    
    public String getSocial_reason(){
        return social_reason;
    }
    public void setSocial_reason(String social_reason){
         this.social_reason = social_reason;
    }

    public String getDirection(){
        return direction;
    }
    public void setDirection(String direction){
         this.direction = direction;
    }

    public Long getTelephone(){
        return telephone;
    }
    public void setTelephone(Long telephone){
         this.telephone = telephone;
    }

    public double getFamiEmpresa_id(){
        return famiEmpresa_id;
    }
    public void setFamiEmpresa_id(double famiEmpresa_id){
         this.famiEmpresa_id = famiEmpresa_id;
    }

    /*public User getUser(){
        return user;
    }
    public void setUser(User user){
         this.user = user;
    }*/
@Override
    public String toString() {
        return "Company [nit=" + nit + ", name=" + name + ", social_reason=" + social_reason + ", direction="
                + direction + ", telephone=" + telephone + ", famiEmpresa_id=" + famiEmpresa_id + "]";//, user=" + user.getName() + "]";
    }





}
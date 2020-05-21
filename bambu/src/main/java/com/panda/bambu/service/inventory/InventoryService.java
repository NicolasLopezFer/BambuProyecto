package com.panda.bambu.service.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.InventoryRepository;
import com.panda.bambu.model.inventory.Kardex;
import com.panda.bambu.model.inventory.MeasureMethod;
import com.panda.bambu.model.inventory.Output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService{
      
      @Autowired
      InventoryRepository inventoryRepository;

      @Autowired
      ArticleInventoryService articleInventoryService;

      @Autowired
      MeasureMethodService measureMethodService;

      @Autowired
      BalanceService balanceService;

      @Autowired
      OutputService outputService;

      @Autowired
      EntryService entryService;

      public Inventory findById(Long id){
          return inventoryRepository.findById(id).get();
      }

      public List<Inventory> findAllInventories(){
          return inventoryRepository.findAll();
      }

      public Inventory create(){
           
           Inventory newInventory = new Inventory();
           inventoryRepository.save(newInventory);
           
           return getLastInventoryCreate();

      }

      public Inventory getLastInventoryCreate(){

             List<Inventory> inventories = findAllInventories();
             return inventories.get(inventories.size()-1);
      }
      
      public double addEntryInventory(Inventory inventory, Entry entry, String method){
          
          double unitCost = 0.0;
        
          if(entry != null && inventory != null ){
              if(inventoryRepository.existsById(inventory.getId()) == true){
                 inventory.getEntries().add(entry);
                 inventoryRepository.save(inventory);
                 if(method.toUpperCase().equals("FIFO")){
                    unitCost = applyMethodFiFo(inventory);
                    //addBalanceInventoryEntry(inventory, entry);
                    
                 }
                 else if(method.toUpperCase().equals("LIFO")){
                    unitCost = applyMethodLiFo(inventory);
                    //addBalanceInventoryEntry(inventory, entry);
                 }
                 else{
                    unitCost = applyMethodPromedioPonderado(inventory);
                    //addBalanceInventoryEntry(inventory, entry);
               
                  }
                  inventoryRepository.save(inventory);
                  return unitCost;                                              
               } 
            
          }

          return unitCost;
          
      }

      public double addOutputInventory(Inventory inventory, Output output, String method){
        
        double unitCost = 0.0;
              
        if(output != null && inventory != null ){
           if(inventoryRepository.existsById(inventory.getId()) == true){
               if(method.toUpperCase().equals("FIFO")){
                  unitCost = applyMethodFiFo(inventory,output);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  //addBalanceInventoryOutput(inventory, output);
                 
               }
               else if(method.toUpperCase().equals("LIFO")){
                  unitCost = applyMethodLiFo(inventory,output);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  //addBalanceInventoryOutput(inventory, output);
                  
               }
               else{
                  unitCost = applyMethodPromedioPonderado(inventory);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  //addBalanceInventoryOutput(inventory, output);
               
               }

              inventory.getOutputs().add(output);
              inventoryRepository.save(inventory);
              return unitCost;                                                  
           } 

        }

        return unitCost;
        
       }
       
       public boolean addBalanceInventory(Inventory inventory,Balance balance, Article article){
             
            if(!balanceService.create(balance, article)){
               inventory.getBalances().add(balance);
               inventoryRepository.save(inventory);
               return true;
            }

            return false;
       }

       public boolean addBalanceInventoryEntry(Inventory inventory,Entry entry){
         
         Balance balance;
         int quantity = 0;
         double totalCost = 0.0;
      

         if(inventory != null ){
            if(inventoryRepository.existsById(inventory.getId()) == true){
               if(inventory.getBalances().isEmpty()){
                  balance = new Balance(entry.getQuantity(),entry.getTotalCost());
                  balanceService.create(balance, entry.getArticle());
                  inventory.getBalances().add(balance);
                  save(inventory);
                  return true;
               }
               else{
                  
                 // aux = inventory.getBalances().get(inventory.getBalances().size()-1);
                 // quantity = aux.getQuantity() + entry.getQuantity();
                 // totalCost = aux.getTotalCost() + entry.getTotalCost();
                 // Cambiar flujo
                  quantity = entry.getQuantity() + entry.getArticle().getQuantity();
                  totalCost = quantity * entry.getArticle().getUnitCost();
                  balance = new Balance(quantity,totalCost);
                  balanceService.create(balance, entry.getArticle());
                  inventory.getBalances().add(balance);
                  save(inventory);
                  return true;

               }
               
                                                                
            }     
 
         }

         return false;
      }
      
      public boolean addBalanceInventoryOutput(Inventory inventory,Output output){
         
         Balance balance;
         Balance aux;
         int quantity = 0;
         double totalCost = 0.0;

         if(inventory != null ){
            if(inventoryRepository.existsById(inventory.getId()) == true){
               if(inventory.getBalances().isEmpty()){
                  return false;
               }
               else{
                  
                  aux = inventory.getBalances().get(inventory.getBalances().size()-1);
                  quantity = aux.getQuantity() - output.getQuantity();
                  totalCost = aux.getQuantity() * output.getUnitCost();
                  balance = new Balance(quantity,totalCost);
                  balanceService.create(balance, output.getArticle());
                  inventory.getBalances().add(balance);
                  save(inventory);
                  return true;

               }
               
                                                                
            }     
 
         }

         return false;
      }

          
      public double modifyMeasureMethodInventory(Inventory inventory, String method){
           
           double unitCost = 0.0;
           System.out.println("METODOS WILLIE");
           List<MeasureMethod> methods = measureMethodService.findAll();
           System.out.println("");
           System.out.println("");
           System.out.println("");
           System.out.println("");
           System.out.println("METODOS WILLIE" + methods.get(0));
           System.out.println("");
           System.out.println("");
           System.out.println("");
           if(methods != null && !methods.isEmpty() && !method.isEmpty() && inventory  != null){
              for(MeasureMethod m: methods){
                  if(m.getName().toUpperCase().equals(method.toUpperCase())){

                     if(method.toUpperCase().equals("FIFO")){
                        unitCost = applyMethodFiFo(inventory);
                        return unitCost;
                     }
                     else if(method.toUpperCase().equals("LIFO")){
                         System.out.println("SOOOOY LFO ");
                         unitCost = applyMethodLiFo(inventory);
                         return unitCost;
                     }
                     else{
                         unitCost = applyMethodPromedioPonderado(inventory);
                         return unitCost;
                     }

                  }
               }  
           }

          return unitCost;         
       }

       
       public double applyMethodFiFo(Inventory inventory){
         
         List<Entry> entries = inventory.getEntries();
         double unitCost = 0.0;
         
         if(!entries.isEmpty()){
            for (Entry entry : entries){
                  int quantityE = entry.getQuantityMethod(); 
                  if( quantityE != 0){
                      unitCost = entry.getUnitCost();
                     return unitCost;
                  }
            }
         }
        
         return unitCost;
       }

       public double applyMethodFiFo(Inventory inventory,Output output){
            
            List<Entry> entries = inventory.getEntries();
            double unitCost = 0.0;

            if(!entries.isEmpty()){
               for (Entry entry: inventory.getEntries()){
                     int quantityE = entry.getQuantityMethod(); 
                     if( quantityE != 0 && output.getQuantity() < quantityE){
                        quantityE -= output.getQuantity();
                        entry.setQuantityMethod(quantityE);
                        unitCost = entry.getUnitCost();
                        return unitCost;
                     }
               }
            }
            
            return unitCost;
       }       

      
      public double applyMethodLiFo(Inventory inventory){
         
         List<Entry> entries = inventory.getEntries();
         double unitCost = 0.0;
    
         if(!entries.isEmpty()){

            for (int i = entries.size()-1; i >=0; i--){
                  int quantityE = entries.get(i).getQuantityMethod();
                  if( quantityE != 0){
                      unitCost =  entries.get(i).getUnitCost();
                      return unitCost;
                  }
            }
         }
        
        return unitCost;
      }

      public double applyMethodLiFo(Inventory inventory, Output output){
         
         List<Entry> entries = inventory.getEntries();
         double unitCost = 0.0;

         if(!entries.isEmpty()){

            for (int i = entries.size()-1; i >=0; i--){
                  int quantityE = entries.get(i).getQuantityMethod();
                  if( quantityE != 0 && output.getQuantity() < quantityE){
                      quantityE -= output.getQuantity();
                      entries.get(i).setQuantityMethod(quantityE);
                      unitCost =  entries.get(i).getUnitCost();
                      return unitCost;
                  }
            }
         }
        
        return unitCost;
      }

      public double applyMethodPromedioPonderado(Inventory inventory){
         
         List<Entry> entries = inventory.getEntries();
         double unitCost = 0.0;

         if(!entries.isEmpty()){
            for (Entry entry: entries){
                 if(entry.getQuantity() > 0){
                    unitCost += entry.getUnitCost();
                 }
                 
            }
            unitCost = unitCost/entries.size();  
            return unitCost;
           
         }

         return unitCost;
      }

      public List<Kardex> createKardex(LocalDate dateInicial, LocalDate dateFinal, Inventory inventory){
              List<Kardex> kardex = new ArrayList<Kardex>();
              if(inventory != null){
                 if(!inventory.getEntries().isEmpty()){
                    for (Entry entry : inventory.getEntries()) {
                        if(entry.getDate().isAfter(dateInicial) && entry.getDate().isBefore(dateFinal))
                        {
                           Kardex element = new Kardex();
                           element.setCode(entry.getCode());
                           element.setQuantity(entry.getQuantity());
                           element.setTotalCost(entry.getTotalCost());
                           element.setDetail(entry.getDetail());
                           element.setUnitCost(entry.getUnitCost());
                           element.setType("Entrada");
                           element.setDate(entry.getDate());
                        }
                    }
                  }

                  if(!inventory.getOutputs().isEmpty()){
                     for (Output output : inventory.getOutputs()) {
                         if(output.getDate().isAfter(dateInicial) && output.getDate().isBefore(dateFinal))
                         {
                            Kardex element = new Kardex();
                            element.setCode(output.getCode());
                            element.setQuantity(output.getQuantity());
                            element.setTotalCost(output.getTotalCost());
                            element.setDetail(output.getDetail());
                            element.setUnitCost(output.getUnitCost());
                            element.setType("Salida");
                            element.setDate(output.getDate());
                         }
                     }
                 }
            }

            return kardex;
         
      }

      public boolean save(Inventory inventory){

         if(inventory.getId() >=0.0 && inventoryRepository.existsById(inventory.getId()) == true){
            inventoryRepository.save(inventory);
            return true; 
         }

         return false;

      } 


} 
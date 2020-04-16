package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.InventoryRepository;
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

           return newInventory;

      }
      
      public double addEntryInventory(Inventory inventory, Entry entry){
          
          double unitCost = 0.0;
        
          if(entry != null && inventory != null ){
              if(inventoryRepository.existsById(inventory.getId()) == true){
                 inventory.getEntries().add(entry);
                 String method = inventory.getMeasureMethod();
                 if(method.toUpperCase().equals("FIFO")){
                    unitCost = applyMethodFiFo(inventory);
                    addBalanceInventoryEntry(inventory, entry);
                 
                 }
                 else if(method.toUpperCase().equals("LIFO")){
                    unitCost = applyMethodLiFo(inventory);
                    addBalanceInventoryEntry(inventory, entry);
                 }
                 else{
                    unitCost = applyMethodPromedioPonderado(inventory);
                    addBalanceInventoryEntry(inventory, entry);
               
                  }
                  inventory.getEntries().add(entry);
                  inventoryRepository.save(inventory);
                  return unitCost;                                              
               } 

          }

          return unitCost;
          
      }

      public double addOutputInventory(Inventory inventory, Output output){
        
        double unitCost = 0.0;

        if(output != null && inventory != null ){
           if(inventoryRepository.existsById(inventory.getId()) == true){
               String method = inventory.getMeasureMethod();
               if(method.toUpperCase().equals("FIFO")){
                  unitCost = applyMethodFiFo(inventory,output);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  addBalanceInventoryOutput(inventory, output);
                 
               }
               else if(method.toUpperCase().equals("LIFO")){
                  unitCost = applyMethodLiFo(inventory,output);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  addBalanceInventoryOutput(inventory, output);
                  
               }
               else{
                  unitCost = applyMethodPromedioPonderado(inventory);
                  output.setUnitCost(unitCost);
                  outputService.save(output);
                  addBalanceInventoryOutput(inventory, output);
               
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
         Balance aux;
         int quantity = 0;
         double totalCost = 0.0;

         if(inventory != null ){
            if(inventoryRepository.existsById(inventory.getId()) == true){
               if(inventory.getBalances().isEmpty()){
                  balance = new Balance(entry.getQuantity(),entry.getTotalCost());
                  balanceService.create(balance, entry.getArticle());
                  inventory.getBalances().add(balance);

                  return true;
               }
               else{
                  
                  aux = inventory.getBalances().get(inventory.getBalances().size()-1);
                  quantity = aux.getQuantity() + entry.getQuantity();
                  totalCost = aux.getTotalCost() + entry.getTotalCost();
                  balance = new Balance(quantity,totalCost);
                  balanceService.create(balance, entry.getArticle());
                  inventory.getBalances().add(balance);
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
                  totalCost = aux.getTotalCost() - output.getTotalCost();
                  balance = new Balance(quantity,totalCost);
                  balanceService.create(balance, output.getArticle());
                  inventory.getBalances().add(balance);
                  return true;

               }
               
                                                                
            }     
 
         }

         return false;
      }

          
      public boolean modifyMeasureMethodInventory(Inventory inventory, String method){
         
           List<MeasureMethod> methods = measureMethodService.findAllMethods();
           if(!methods.isEmpty() && !method.isEmpty() && inventory  != null){
              for(MeasureMethod m: methods){
                  if(m.getName().toUpperCase().equals(method.toUpperCase())){
                     inventory.setMeasureMethod(method);
                     inventoryRepository.save(inventory);

                     if(method.toUpperCase().equals("FIFO")){
                        applyMethodFiFo(inventory);
                     }
                     else if(method.toUpperCase().equals("LIFO")){
                         applyMethodLiFo(inventory);
                     }
                     else{
                         applyMethodPromedioPonderado(inventory);
                     }

                     return true;
                  }
               }  
           }

          return false;         
       }

       
       public double applyMethodFiFo(Inventory inventory){
         
         List<Entry> entries = inventory.getEntries();
         double unitCost = 0.0;

         if(!entries.isEmpty()){
            for (Entry entry: inventory.getEntries()){
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
                 unitCost += entry.getUnitCost();
            }
            unitCost = unitCost/entries.size();  
            return unitCost;
           
         }

         return unitCost;
      }

      public boolean save(Inventory inventory){

         if(inventory.getId() >=0.0 && inventoryRepository.existsById(inventory.getId()) == true){
            inventoryRepository.save(inventory);
            return true; 
         }

         return false;

      } 


} 
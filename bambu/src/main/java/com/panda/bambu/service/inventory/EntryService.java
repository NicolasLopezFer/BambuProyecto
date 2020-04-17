package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.EntryRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {


     @Autowired
     private EntryRepository entryRepository;

     @Autowired
	 private ArticleService articleService;

     public Entry findById(Long id){
         return entryRepository.findById(id).get();
     }

     public Entry findByCode(String code){
         return entryRepository.findByCode(code);  
     }

     public List<Entry> findAll(){
         return entryRepository.findAll();
     }
     
    public boolean isEntryAlreadyPresent(Entry entry) {
		Entry entryFound = entryRepository.findByCode(entry.getCode());
		if (entryFound==null) 
			return false;
		return true;
    }
   
    public boolean create(String code, String detail, int quantity, double unitCost, double totalCost, Article article){
      
        if(!code.isEmpty() && quantity >= 0 && unitCost > 0.0 && totalCost > 0.0 && article != null && articleService.isArticleAlreadyPresent(article)){
            Entry newEntry = new Entry(code, detail, quantity, unitCost, totalCost);
            newEntry.setArticle(article);
            entryRepository.save(newEntry);
            return true;
        }
        
        return false;

    }
    
    public boolean createEntry(Entry entry){
                 
        if(entry != null && entryRepository.findByCode(entry.getCode()) == null && articleService.isArticleAlreadyPresent(entry.getArticle())){
            entryRepository.save(entry);
            return true;
        }
        
        return false;

    }

    public boolean delete(Long id){

        if(entryRepository.existsById(id)){
           entryRepository.deleteById(id);
           return true;
        }

        return false;
    }
    
    public boolean delete(Entry entry){

        if(entry != null && entryRepository.findByCode(entry.getCode()) != null){
           entryRepository.delete(entry);
           return true;
        }

        return false;
        
    }

    public boolean save(Entry entry){
       
        if(entry != null && entryRepository.findByCode(entry.getCode()) != null){
            entryRepository.save(entry);
            return true;
         }

        return false;

    }

    public void deleteAllEntries(){
        entryRepository.deleteAll();
    }

}
package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.inventory.MeasureMethod;
import com.panda.bambu.model.inventory.MeasureMethodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureMethodService {
      
      @Autowired
      MeasureMethodRepository measureMethodRepository;

      public List<MeasureMethod> findAll(){
           if(!measureMethodRepository.findAll().isEmpty()){
              return measureMethodRepository.findAll();
           }
        return null;
      }

}
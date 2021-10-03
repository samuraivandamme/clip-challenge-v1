package com.example.clip.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.clip.entities.Payment;
import com.example.clip.model.PayLoadDto;

/**
 * @author Ivan
 *
 */
@Configuration
public class ClipMapper {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mappper = new ModelMapper();

		   PropertyMap<Payment, PayLoadDto> propertyMap = new PropertyMap<Payment,PayLoadDto> (){
		        protected void configure() {
		        	map().setId(source.getId());
		        	map().setAmount(source.getAmount());
		        	map().setStatus(source.getStatus());
		        	map().setUser(source.getUser());
		            
		        }
		    };

		    mappper.addMappings(propertyMap);
	    return mappper;
	}

}

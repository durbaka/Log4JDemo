package com.example;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class LogDemoMs2Application {

	public int multiplier = 10;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	Tracer tracer;
	@Autowired
	private SpanAccessor accessor;

	/*Logger logger = LoggerFactory.getLogger(LogDemoMs1Application.class);*/
	static Logger log = Logger.getLogger(LogDemoMs2Application.class.getName());
	Span newSpan1;

	@RequestMapping(value="/input/{appId}",method=RequestMethod.GET)
	public String getDecision(@PathVariable("appId") int inputId){
		
		return ("output after multiplier is "+ multiplier * inputId);
		
	}
	
	@RequestMapping(value="/1/{input}",method=RequestMethod.GET)
	public String service1(@PathVariable("input") int inputId) {

		newSpan1 = this.tracer.createSpan("1-service", new AlwaysSampler());
		try {
			/*Span span = this.accessor.getCurrentSpan();*/
            
			log.info("service 1");
			this.tracer.addTag("name", "1-service-tag");
			Span currentSpan = LogDemoMs2Application.this.accessor.getCurrentSpan();
			
			/*System.out.println(currentSpan.idToHex(currentSpan.getTraceId())+" traceID");
			System.out.println(currentSpan.idToHex(currentSpan.getSpanId())+" spanID");
			List<Long> parent = currentSpan.getParents();
			System.out.println(currentSpan.idToHex(parent.get(0)) + " parent");
			System.out.println(currentSpan.get+" processID");
			System.out.println(currentSpan.idToHex(currentSpan.getParents()));
			System.out.println(currentSpan);*/
		
			
			newSpan1.logEvent("1-service-bc2");
			 restTemplate.postForObject("http://192.168.56.1:9002/2",currentSpan, Object.class);
			newSpan1.logEvent("1-service-ac2");

			return "service 1" + " " +  "";
		} finally {
			this.tracer.close(newSpan1);
		}

	}

  
  @RequestMapping(value = "/2", method = RequestMethod.POST, consumes = "application/json")
		void service2(@RequestBody Span data) {
	  Span newSpan2 = this.tracer.createSpan("2-service",data);
	  	//Span s = new Span();
         //Span s = Span(data);
		/*Span newSpan2 = this.tracer.createSpan("2-service",Span currentSpan);*/
		/*Span continuedSpan = this.tracer.continueSpan(newSpan1);*/
	  
       /* System.out.println(data);
        System.out.println(data.getClass());*/
       
		
		try {

			/*log.trace("service 2");
			log.debug("service 2");*/
			log.info("service 2");
			/*log.warn("service 2");
			log.error("service 2");*/
			/*  this.tracer.addTag("name", "2-service-tag");*/
			/*return "service 2";*/
		}

		finally {
			/*this.tracer.close(currentSpan);*/
			this.tracer.close(newSpan2);
		}
	}

	
	public static void main(String[] args) {
		SpringApplication.run(LogDemoMs2Application.class, args);
	}
	
	
}

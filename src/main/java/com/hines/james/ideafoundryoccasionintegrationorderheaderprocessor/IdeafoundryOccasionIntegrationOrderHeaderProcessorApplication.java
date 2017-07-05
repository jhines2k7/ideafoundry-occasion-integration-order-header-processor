package com.hines.james.ideafoundryoccasionintegrationorderheaderprocessor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@EnableBinding(Processor.class)
@SpringBootApplication
public class IdeafoundryOccasionIntegrationOrderHeaderProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeafoundryOccasionIntegrationOrderHeaderProcessorApplication.class, args);
	}

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Message<String> transform(String payload) {

		JSONObject obj = new JSONObject(payload);

		String order = obj.getJSONObject("order").toString();
		String person = obj.getJSONObject("person").toString();
		String questions = obj.getJSONArray("questions").toString();

		return  MessageBuilder.withPayload(payload)
				.setHeader("order", order)
				.setHeader("person", person)
				.setHeader("questions", questions)
				.build();
	}
}

package com.hines.james.ideafoundryoccasionintegrationorderheaderprocessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public abstract class IdeafoundryOccasionIntegrationOrderHeaderProcessorApplicationTests {

	@Autowired
	protected IdeafoundryOccasionIntegrationOrderHeaderProcessorApplication processor;

	@Autowired
	protected MessageCollector messageCollector;

	@SpringBootTest()
    public static class HeaderProcessorTests extends IdeafoundryOccasionIntegrationOrderHeaderProcessorApplicationTests {
	    @Ignore
	    @Test
        public void testWiring() throws IOException {
            File orderJsonFile = getClassPathResource("modified-occasion-order.json").getFile();
            String orderJson = readFileToString(orderJsonFile, "UTF-8");

            Message<String> output = processor.transform(orderJson);

            assertThat(output.getPayload(), equalTo("hello world"));
        }

        private ClassPathResource getClassPathResource(String path) {
            return new ClassPathResource(path, getClass());
        }
    }
}

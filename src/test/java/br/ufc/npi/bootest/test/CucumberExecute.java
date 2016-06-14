package br.ufc.npi.bootest.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import br.ufc.npi.bootest.BootestApplication;

@RunWith(Cucumber.class)
@SpringApplicationConfiguration(classes = BootestApplication.class)
@WebAppConfiguration
@CucumberOptions (features = "classpath:features",glue={"br.ufc.npi.bootest.test"})
public class CucumberExecute {}
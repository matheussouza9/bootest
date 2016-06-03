package br.ufc.npi.bootest.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.ufc.npi.bootest.BootestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootestApplication.class)
@WebAppConfiguration
public class GenericTest {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
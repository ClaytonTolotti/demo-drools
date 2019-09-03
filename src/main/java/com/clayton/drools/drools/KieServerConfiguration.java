package com.clayton.drools.drools;

import static com.clayton.drools.constants.Constants.PASS_KIE_SERVER;
import static com.clayton.drools.constants.Constants.URL_KIE_SERVER;
import static com.clayton.drools.constants.Constants.USER_KIE_SERVER;

import java.util.HashSet;
import java.util.Set;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.springframework.stereotype.Component;

import com.clayton.drools.obj.AnalyzesObj;

@Component
public class KieServerConfiguration {

	private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

	public KieServicesClient initialize() {
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL_KIE_SERVER, USER_KIE_SERVER, PASS_KIE_SERVER);
		conf.setMarshallingFormat(FORMAT);
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(AnalyzesObj.class);
		conf.addExtraClasses(classes);
		return KieServicesFactory.newKieServicesClient(conf);
	}
}

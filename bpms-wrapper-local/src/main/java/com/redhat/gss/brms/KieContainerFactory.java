package com.redhat.gss.brms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;

@Singleton
@Startup
public class KieContainerFactory {
	
	// TODO improve this cache
	private Map<String, KieContainer> kieContainersCache;

	final String MVN_HOME = "/home/wsiqueir/.m2/settings.xml";
	
	KieServices kieServices;

	@PostConstruct
	public void initialize() {
		System.setProperty("kie.maven.settings.custom", MVN_HOME);
		kieServices = KieServices.Factory.get();
		kieContainersCache = new HashMap<String, KieContainer>();
	}
	
	public KieContainer getKieContainer(String groupId, String artifactId, String version) {
		String GAV = groupId + ":" + artifactId  + ":" + version;
		KieContainer kieContainer = kieContainersCache.get(GAV);
		if(kieContainer == null) {
			ReleaseId id = kieServices.newReleaseId(groupId, artifactId, version);
			kieContainer = kieServices.newKieContainer(id);
			kieContainersCache.put(GAV, kieContainer);
		}
		return kieContainer;		
	}

}

package com.dtci.linear.analytics.kafkastreams;

import java.util.Arrays;
import java.util.Objects;

/**
 * Entry Point class to delegate to underlying Stream Processing App
 * 
 * @author chawl001
 */
public final class Application {
	public enum MODULE {
		CON_COMMON__FLIGHT_RANGE("con-common.flght_rng", "Common Flight Range", CommonFlightRangeConformanceApp.class),
		PRPSL__PROPOSAL_LINE("prpsl.prpsl_ln", "Proposal Line", ProposalLineConformanceApp.class),
		PRPSL__UNIT_LENGTH("prpsl.unit_length", "Prerequisite: Unit Length", UnitLengthForwardApp.class);
		
		private final String name;
		private final String description;
		private final Class<? extends AbstractKafkaStreamsApp> streamsProcessor;
		
		private MODULE (String name, String desc, Class<? extends AbstractKafkaStreamsApp> streamsProcessor) {
			this.name = name;
			this.description = desc;
			this.streamsProcessor = streamsProcessor;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public Class<? extends AbstractKafkaStreamsApp> getStreamsProcessor() {
			return this.streamsProcessor;
		}
		
		public static Class<? extends AbstractKafkaStreamsApp> getProcessorByName(String moduleName) {
			Objects.requireNonNull(moduleName, "For lookup, Name of module must be provided");
			
			for (MODULE m : values()) {
				System.out.println("chekcing module: "+ m.getName());
				if (m.getName().equals(moduleName)) {

					System.out.println("Found match for module: "+ moduleName +" returning "+ m.getStreamsProcessor().getCanonicalName());
					return m.getStreamsProcessor();
				}
			}
			
			return null;
		}
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("This program takes two argument: \n\t1. Name of module e.g. con-common.flght_rng. \n\t2. The path to an environment configuration file.");
        }
        
        final String moduleName = args[0].trim().toLowerCase();
        System.out.println("Received module name: "+ moduleName);
        Class<? extends AbstractKafkaStreamsApp> streamsProcessorApp = MODULE.getProcessorByName(moduleName);
        Objects.requireNonNull(streamsProcessorApp, "Unsupported or invalid module: *"+ moduleName +"*");
        
        final AbstractKafkaStreamsApp app = streamsProcessorApp.newInstance();
        System.out.printf("=============================================%n"
		        		+ "Starting kafka-streams app for module: %s%n"
		        		+ "=============================================%n", moduleName);
        String[] revisedArgs = Arrays.copyOfRange(args, 1, 2);
        app.processStreams(revisedArgs);
	}

}

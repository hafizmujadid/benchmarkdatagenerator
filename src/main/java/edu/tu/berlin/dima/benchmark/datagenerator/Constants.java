package edu.tu.berlin.dima.benchmark.datagenerator;

/**
 * Constants to configure input rate and kafka server etc.
 * @author mujadid
 */
class Constants {
    static int INPUT_RATE=6000;

	static String BOOTSTRAP_SERVERS = "ibm-power-6:9092";

	static String TOPIC_PERSON="person";
	static String TOPIC_AUCTION="auction";
	static String TOPIC_BID="bid";
}

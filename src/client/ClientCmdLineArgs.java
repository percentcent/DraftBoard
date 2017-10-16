package client;

import org.kohsuke.args4j.Option;


public class ClientCmdLineArgs {
	@Option(required = true, name = "-h", aliases = {"--host"}, usage = "Hostname")
	private String host;
	
	@Option(required = true, name = "-u", aliases = {"--user"}, usage = "Username")
	private String user;
	
	public String getHost() {
		return host;
	}
	
	public String getUser() {
		return user;
	}
}

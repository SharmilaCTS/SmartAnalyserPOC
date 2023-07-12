package job;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteJob {

	public void executeMail(String suiteName, String jobType) throws IOException {
	   
		System.out.println(jobType);
		Properties prop = new Properties();
		InputStream input = null;

		String jenkinsIP = "";
		String jenkinsUser = "";
		String jenkinsPassword = "";
		String jenkinsBugJob = "";
		String jenkinsStoryJob = "";
		String jenkinsPort = "";
		String jenkinsJob = "";
		String path = "";

		try {
			input = new FileInputStream("mail/config.properties");
			prop.load(input);
			jenkinsIP = prop.getProperty("jenkinsIP");
			jenkinsPort = prop.getProperty("jenkinsPort");
			jenkinsUser = prop.getProperty("jenkinsUser");
			jenkinsPassword = prop.getProperty("jenkinsPassword");
			jenkinsBugJob = prop.getProperty("jenkinsBugJob");
			jenkinsStoryJob = prop.getProperty("jenkinsStoryJob");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (jobType.contains("story")) {
			jenkinsJob = jenkinsStoryJob;
//			 path = "java -jar mail/jenkins-cli.jar -s http://" + jenkinsIP + ":" + jenkinsPort
//					+ " -auth " + jenkinsUser + ":" + jenkinsPassword + " build " + jenkinsJob + " -p storyNumber=\""
//					+ suiteName + "\"";
			 path = "java -jar mail/jenkins-cli.jar -s http://" + "10.153.86.127" + ":" + "8080"
						+ " -auth " + "admin" + ":" + "admin" + " build " + "Sample" + " -p storyNumber=\""
						+ suiteName + "\"";
		}
		if (jobType.contains("KEY")) {
			jenkinsJob = jenkinsBugJob;
//			 path = "java -jar mail/jenkins-cli.jar -s http://" + jenkinsIP + ":" + jenkinsPort
//					+ " -auth " + jenkinsUser + ":" + jenkinsPassword + " build " + jenkinsJob + " -p suiteName=\""
//					+ suiteName + "\"";
			 path = "java -jar mail/jenkins-cli.jar -s http://" + "localhost" + ":" + "8080/" + " -auth " + "admin" + ":" + "admin" + " build " + "SmartAnalyserTests";
		}
		
		System.out.println(path);

		Process process = Runtime.getRuntime().exec(path);
		System.out.println(process);
//		ProcessBuilder builder = new ProcessBuilder(path);
//		builder.redirectErrorStream(true);
//		Process p = builder.start();
		
	}

	private File getLatestFilefromDir() {

		String dirPath = "C:\\mailItems";
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

}

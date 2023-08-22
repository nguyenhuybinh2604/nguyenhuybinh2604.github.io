package techmaster.vn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import techmaster.vn.demo.database.ToDoDB;
import techmaster.vn.demo.utils.IFileReader;

import java.io.File;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private IFileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = System.getProperty("user.dir").concat(File.separator).concat("src/main/resources/").concat("todoList.xlsx");
		ToDoDB.toDoList = fileReader.readFile(filePath);
	}

}

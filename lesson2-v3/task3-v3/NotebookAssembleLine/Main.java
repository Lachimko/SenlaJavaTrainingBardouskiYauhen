import model.classes.AsemblyLaptopLine;
import model.classes.LaptopProduct;
import model.classes.partinstallers.DisplayInstaller;
import model.classes.partinstallers.MotherboardInstaller;
import model.classes.partinstallers.ShellInstaller;

public class Main {

	public static void main(String[] args) {
		
		AsemblyLaptopLine assemblyLine = new AsemblyLaptopLine(new ShellInstaller(), new MotherboardInstaller(), new DisplayInstaller());
		assemblyLine.assembleProduct(new LaptopProduct());
		System.out.println("****" + "\n" + "LapTop Ready.");
	}

}

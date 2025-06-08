import java.util.Scanner;

public class Main {  public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        CRUD c = new CRUD();
	        c.ambilData();
	        
	        int menu = 0;
	        while (menu != 99) {
	            c.menu();
	            System.out.print("Masukan menu yang anda inginkan: ");
	            
	            try {
	                menu = Integer.parseInt(sc.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Input harus berupa angka!");
	                continue;
	            }

	            switch (menu) {
	                case 1:
	                    c.tambahData();
	                    break;
	                case 2:
	                    c.display();
	                    break;
	                // case 3: c.hapusData(); break;
	                // case 4: c.ubahData(); break;
	                case 5: c.cariData(); break;
	                case 99:
	                    System.out.println("Selamat Tinggal");
	                    break;
	                default:
	                    System.out.println("Menu tidak tersedia");
	                    break;
	            }
	        }
}
}

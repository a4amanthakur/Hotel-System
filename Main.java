import java.util.Scanner;
import java.util.InputMismatchException;

class Main
{
	private int choice;
	private Scanner sc;
	private HotelModel hotel;//creating object of abstract class HotelModel
	public Main()
	{

		sc=new Scanner(System.in);
		hotel=new HotelSystem();//but allocating memory to HotelSystem class
		showMenu();
	}
	public void showMenu()
	{
		do
		{
			System.out.println("===========================================");
			System.out.println("1. Book a room.");
			System.out.println("2. Make a payment.");
			System.out.println("3. Print sales report.");
			System.out.println("4. Exit.");
			System.out.println("===========================================");
			System.out.println("Select Option (from 1 to 4)");
			try
			{
				choice=sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				sc.nextLine();//just to catch enter charactr(\n)
				System.out.println("* Error : Input must be 1 to 4. *");
			}
			switch(choice)
			{
				case 1:
				{
					hotel.bookRoom();
					break;
				}
				case 2:
				{
					hotel.makePayment();
					break;
				}
				case 3:
				{
					hotel.printSalesReport();
					break;
				}
				case 4:
				{
					System.out.println("Exit.");
					System.exit(0);
					break;
				}
				default:
				{
					System.out.println("* Error : Input must be 1 to 4. *");
					break;
				}
			}//end of switch case
		}while(choice!=4);
	}
	public static void main(String args[])
	{
		new Main();
	}
}
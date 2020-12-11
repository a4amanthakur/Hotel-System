import java.util.Scanner;
class HotelSystem extends HotelModel
{
	private Scanner sc;
	private String fname;
	private String lname;
	private String resNum;
	private int roomGrade;
	private String grade;
	private double amount;
	private int fetchRoomPos[]=new int[2];
	public HotelSystem()
	{
		fetchRoomPos[0]=-1;
		fetchRoomPos[1]=-1;
		sc=new Scanner(System.in);
	}

	@Override
	public void bookRoom()
	{
		System.out.println("Enter first name:");
		fname=sc.nextLine();

		System.out.println("Enter last name:");
		lname=sc.nextLine();

		System.out.println("Choose room grade: (enter number from 1 to 3 to select room grade) :");
		System.out.println("1. Standard($100).\n2. Deluxe($250).\n3. Suite($600).\n[PRESS 1 or 2 or 3 only].");
		roomGrade=sc.nextInt();
		grade=findGrade(roomGrade);
		amount=findAmount(roomGrade);
		fetchRoomPos=isRoomAvailable(roomGrade);//return room number if available else it return -1;
		if(fetchRoomPos[1]>=0)
		{
			//reserve room
			resNum=reserveRoom(fetchSelectedBuilding(),fetchRoomPos);
			addRoomRegInfo(resNum,fname,lname,grade,amount,fetchRoomPos[1],fetchRoomPos[0],fetchSelectedBuilding(),"Pending");
			System.out.println("Reservation successfull.");
		}
		else
		{
			System.out.println("Sorry...That room grade is not available.");
		}




		sc.nextLine();//just to put new line char.
	}

	@Override
	public void makePayment()
	{
		//list all resrvations
		System.out.println(fetchReservations());
		System.out.println("Do you want to make payment or not: [PRESS Y/n]");
		fname=sc.nextLine();
		if(fname.charAt(0)=='Y' || fname.charAt(0)=='y')
		{
			System.out.println("Enter Res. ID(from above) to Make Payment:");
			sc=new Scanner(System.in);
			resNum=sc.nextLine();
			updatePaymentStatus(resNum);
			
		}
	}

	@Override
	public void printSalesReport()
	{
		System.out.println("---:Hotel total income(Sales report):---");
		System.out.println("#Building A sales report:");
		System.out.println("1.Standard grade : $"+fetchStandardSaleA());
		System.out.println("2.Deluxe grade   : $"+fetchDeluxeSaleA());
		System.out.println("3.Suite grade    : $"+fetchSuiteSaleA());


		System.out.println("#Building B sales report:");
		System.out.println("1.Standard grade : $"+fetchStandardSaleB());
		System.out.println("2.Deluxe grade   : $"+fetchDeluxeSaleB());
		System.out.println("3.Suite grade    : $"+fetchSuiteSaleB());
				
	}
	@Override
	public String findGrade(int roomGrade)
	{
		if(roomGrade==1)
		{
			grade="Standard";
		}
		else if(roomGrade==2)
		{
			grade="Deluxe";
		}
		else if(roomGrade==3)
		{
			grade="Suite";
		}
		return grade;

	}
	@Override
	public double findAmount(int roomGrade)
	{
		if(roomGrade==1)
		{
			amount=100.0;
		}
		else if(roomGrade==2)
		{
			amount=250.0;
		}
		else if(roomGrade==3)
		{
			amount=600.0;
		}
		return amount;

	}
}
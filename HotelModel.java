import java.util.*;
abstract class HotelModel
{
	private int buildingA[][],buildingB[][];
	private double standardSaleA;
	private double deluxeSaleA;
	private double suiteSaleA;
	private double standardSaleB;
	private double deluxeSaleB;
	private double suiteSaleB;
	private int i,j;
	private StringBuilder list;
	//data structure to maintian record of customers
	private int numOfCustomer=0;
	LinkedList<String> reserveId;
	LinkedList<String> firstName;
	LinkedList<String> lastName;
	LinkedList<String> roomGrade;
	LinkedList<Double> amountToPay;
	LinkedList<Integer> roomNumber;
	LinkedList<Integer> floorNumber;
	LinkedList<String> buildingAB;
	LinkedList<String> paySts;
	private int roomPos[]=new int[2];
	private String selectedBuilding="";

	public HotelModel()
	{
		numOfCustomer=0;
		list=new StringBuilder("");
		reserveId=new LinkedList<String>();
		firstName=new LinkedList<String>();
		lastName=new LinkedList<String>();
		roomGrade=new LinkedList<String>();
		amountToPay=new LinkedList<Double>();
		roomNumber=new LinkedList<Integer>();
		floorNumber=new LinkedList<Integer>();
		buildingAB=new LinkedList<String>();
		paySts=new LinkedList<String>();

		buildingA=new int[4][5];//four floor and 5 rooms
		buildingB=new int[4][4];//four floor and 4 rooms	
	}
	//abstract method
	public abstract void bookRoom();
	public abstract void makePayment();
	public abstract void printSalesReport();
	public abstract String findGrade(int roomGrade);
	public abstract double findAmount(int roomGrade);

	public void addRoomRegInfo(String resId,String fname,String lname,String grade,double amount,int room,int floor,String building,String psts)
	{
		this.reserveId.add(this.numOfCustomer,resId);
		this.firstName.add(this.numOfCustomer,fname);
		this.lastName.add(this.numOfCustomer,lname);
		this.roomGrade.add(this.numOfCustomer,grade);
		this.amountToPay.add(this.numOfCustomer,amount);
		this.roomNumber.add(this.numOfCustomer,room);
		this.floorNumber.add(this.numOfCustomer,floor);
		this.buildingAB.add(this.numOfCustomer,building);
		this.paySts.add(this.numOfCustomer,psts);
		this.numOfCustomer++;
	}
	public void updateStandardSale(double amount,String building)
	{
		if(building.equals("A"))
		{
			this.standardSaleA+=amount;	
		}
		else if(building.equals("B"))
		{
			this.standardSaleB+=amount;	
		}
		
	}
	public void updateDeluxeSale(double amount,String building)
	{
		if(building.equals("A"))
		{
			this.deluxeSaleA+=amount;	
		}
		else if(building.equals("B"))
		{
			this.deluxeSaleB+=amount;	
		}
	}
	public void updateSuiteSale(double amount,String building)
	{
		if(building.equals("A"))
		{
			this.suiteSaleA+=amount;	
		}
		else if(building.equals("B"))
		{
			this.suiteSaleB+=amount;	
		}
	}

	public double fetchStandardSaleA()
	{
		return standardSaleA;
	}
	public double fetchDeluxeSaleA()
	{
		return deluxeSaleA;
	}
	public double fetchSuiteSaleA()
	{
		return suiteSaleA;
	}

	public double fetchStandardSaleB()
	{
		return standardSaleB;
	}
	public double fetchDeluxeSaleB()
	{
		return deluxeSaleB;
	}
	public double fetchSuiteSaleB()
	{
		return suiteSaleB;
	}

	public void updatePaymentStatus(String resNum)
	{
		i=this.reserveId.indexOf(resNum);
		//System.out.println("value of i:"+i);
		if(i!=-1 )
		{
			if(paySts.get(i).equals("Pending"))
			{
				if(buildingAB.get(i).equals("A"))
				{
					if(amountToPay.get(i)==100.0)
					{
						updateStandardSale(100.0,"A");
					}
					else if(amountToPay.get(i)==250.0)
					{
						updateDeluxeSale(250.0,"A");
					}
					else if(amountToPay.get(i)==600.0)
					{
						updateSuiteSale(600.0,"A");
					}
				}
				else if(buildingAB.get(i).equals("B"))
				{
					if(amountToPay.get(i)==100.0)
					{
						updateStandardSale(100.0,"B");
					}
					else if(amountToPay.get(i)==250.0)
					{
						updateDeluxeSale(250.0,"B");
					}
					else if(amountToPay.get(i)==600.0)
					{
						updateSuiteSale(600.0,"B");
					}
				}
				System.out.println("Payment successfull...");
			}
			else
			{
				System.out.println("Already Paid.");
			}
			

			this.paySts.add(i,"Paid");
		}
		else
		{
			System.out.println("* Error: No such Reservation ID. *");
		}

	}
	public String fetchReservations()
	{
		Iterator<String> itr1=reserveId.iterator();
		Iterator<String> itr2=firstName.iterator();
		Iterator<String> itr3=lastName.iterator();
		Iterator<String> itr4=roomGrade.iterator();
		Iterator<Double> itr5=amountToPay.iterator();
		Iterator<Integer> itr6=roomNumber.iterator();
		Iterator<Integer> itr7=floorNumber.iterator();
		Iterator<String> itr9=buildingAB.iterator();
		Iterator<String> itr8=paySts.iterator();
		list.delete(0,list.length());
		list.append("|Res. ID|Full Name|Grade|Amount|Room No.|Floor No.|Building|Payment Status|\n");
		while(itr1.hasNext() && itr2.hasNext() && itr3.hasNext() && itr4.hasNext() && itr9.hasNext() && itr5.hasNext()  && itr6.hasNext() &&itr7.hasNext() &&itr8.hasNext() )
		{
			list.append("|"+itr1.next()+" | "+itr2.next()+" "+itr3.next()+" | "+itr4.next()+" | $"+itr5.next()+" | "+(itr6.next()+1)+" | "+(itr7.next()+1)+" | "+itr9.next()+" | "+itr8.next()+"|\n");
		}
		return list.toString();
	}

	public int[] isRoomAvailable(int roomGrade)
	{
		roomPos[0]=-1;
		roomPos[1]=-1;
		
		if(roomGrade==1)
		{
			selectedBuilding="A";
			roomPos=searchInBuildingA(roomGrade);
			if(roomPos[1]<0)
			{
				selectedBuilding="B";
				roomPos=searchInBuildingB(roomGrade);
				if(roomPos[1]<0)
				{
					selectedBuilding="";
					roomPos[0]=-1;
					roomPos[1]=-1;
				}
			}
		}
		else if(roomGrade==2)
		{
			selectedBuilding="A";
			roomPos=searchInBuildingA(roomGrade);
			if(roomPos[1]<0)
			{
				selectedBuilding="B";
				roomPos=searchInBuildingB(roomGrade);
				if(roomPos[1]<0)
				{
					selectedBuilding="";
					roomPos[0]=-1;
					roomPos[1]=-1;
				}
			}
		}
		else if(roomGrade==3)
		{
			selectedBuilding="A";
			roomPos=searchInBuildingA(roomGrade);
			if(roomPos[1]<0)
			{
				selectedBuilding="B";
				roomPos=searchInBuildingB(roomGrade);
				if(roomPos[1]<0)
				{
					selectedBuilding="";
					roomPos[0]=-1;
					roomPos[1]=-1;
				}
			}
		}

		return roomPos;
	}

	public int[] searchInBuildingA(int grade)
	{
		
		selectedBuilding="";
		roomPos[0]=-1;
		roomPos[1]=-1;
		if(grade==1)
		{
			for(j=0;j<2;j++)
			{
				for(i=0;i<buildingA[j].length;i++)
				{
					if(buildingA[j][i]==0)
					{
						roomPos[0]=j;
						roomPos[1]=i;
						break;
					}
				}//innr for
				if(roomPos[0]>=0)
				{
					break;
				}
			}//outr for
		}
		else if(grade==2)
		{
				for(i=0;i<buildingA[0].length;i++)
				{
					if(buildingA[2][i]==0)
					{
						roomPos[0]=2;
						roomPos[1]=i;
						break;
					}
				}
		}
		else if(grade==3)
		{
				for(i=0;i<buildingA[0].length;i++)
				{
					if(buildingA[3][i]==0)
					{
						roomPos[0]=3;
						roomPos[1]=i;
						break;
					}
				}
		}
		selectedBuilding="A";
		return roomPos;
	}//end of fun

	public int[] searchInBuildingB(int grade)
	{
		
		selectedBuilding="";
		roomPos[0]=-1;
		roomPos[1]=-1;
		if(grade==1)
		{
			for(j=0;j<2;j++)
			{
				for(i=0;i<buildingB[j].length;i++)
				{
					if(buildingB[j][i]==0)
					{
						roomPos[0]=j;
						roomPos[1]=i;
						break;
					}
				}//innr for
				if(roomPos[0]>=0)
				{
					break;
				}
			}//outr for
		}
		else if(grade==2)
		{
				for(i=0;i<buildingB[0].length;i++)
				{
					if(buildingB[2][i]==0)
					{
						roomPos[0]=2;
						roomPos[1]=i;
						break;
					}
				}
		}
		else if(grade==3)
		{
				for(i=0;i<buildingB[0].length;i++)
				{
					if(buildingB[3][i]==0)
					{
						roomPos[0]=3;
						roomPos[1]=i;
						break;
					}
				}
		}
		selectedBuilding="B";
		return roomPos;
	}

	public String fetchSelectedBuilding()
	{
		return selectedBuilding;
	}

	public String reserveRoom(String building,int[] floorRoom)
	{
		String uid="";
		if(building.equals("A"))
		{
			buildingA[floorRoom[0]][floorRoom[1]]=1;
			uid="BA"+floorRoom[0]+""+floorRoom[1];
		}
		else if(building.equals("B"))
		{
			buildingB[floorRoom[0]][floorRoom[1]]=1;	
			uid="BB"+floorRoom[0]+""+floorRoom[1];
		}
		return uid;
	}//end of reserveRoom method

}//end of class